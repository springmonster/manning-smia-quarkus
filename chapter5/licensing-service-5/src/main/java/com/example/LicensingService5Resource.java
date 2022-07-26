package com.example;

import com.example.entity.Fruit;
import com.example.entity.License;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.CompositeException;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Path("v1/organization/{organizationId}/license")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LicensingService5Resource {

    private static final Logger LOGGER = Logger.getLogger(LicensingService5Resource.class.getName());

    @GET
    @Path("{licenseId}")
    public Uni<License> getLicense(@PathParam("licenseId") String licenseId, @PathParam("organizationId") String organizationId) {
        return Panache
                .withTransaction(() -> getLicenseUniByLicenseIdAndOrganizationId(licenseId, organizationId));
    }

    private Uni<License> getLicenseUniByLicenseIdAndOrganizationId(String licenseId, String organizationId) {
        return License.<License>find(
                        "SELECT id, licenseId, description, organizationId, productName, licenseType, comment FROM License WHERE licenseId = :licenseId and organizationId = :organizationId",
                        Parameters.with("licenseId", licenseId).and("organizationId", organizationId))
                .firstResult();
    }

    private Uni<Integer> updateLicenseUniByLicenseIdAndOrganizationId(String licenseId, String organizationId, String comment) {
        return License.update("UPDATE License SET comment = :comment WHERE licenseId = :licenseId and organizationId = :organizationId", Parameters.with("licenseId", licenseId).and("organizationId", organizationId).and("comment", comment));
    }

    @GET
    public Uni<List<License>> getAllLicenses() {
        return License.listAll();
    }

    @POST
    public Uni<Response> createLicense(License license, @PathParam("organizationId") String organizationId) {
        if (license == null) {
            throw new WebApplicationException("license can't be null when creating!");
        }
        license.setLicenseId(UUID.randomUUID().toString());
        license.setOrganizationId(organizationId);
        return Panache
                .withTransaction(license::persist)
                .replaceWith(Response.ok(license).build());
    }

    @PUT
    @Path("{licenseId}")
    public Uni<Response> updateLicense(@PathParam("licenseId") String licenseId, @PathParam("organizationId") String organizationId) {
        return Panache
                .withTransaction(() -> updateLicenseUniByLicenseIdAndOrganizationId(licenseId, organizationId, "This is update"))
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND).build());
    }

    @DELETE
    @Path("{licenseId}")
    public Uni<Response> deleteLicense(@PathParam("licenseId") String licenseId) {
        return Panache
                .withTransaction(() -> Fruit.delete("DELETE from License WHERE licenseId = :licenseId",
                                Parameters.with("licenseId", licenseId))
                        .map(deleted -> deleted > 0
                                ? Response.ok().status(NO_CONTENT).build()
                                : Response.ok().status(NOT_FOUND).build()));
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            Throwable throwable = exception;

            int code = 500;
            if (throwable instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            // This is a Mutiny exception and it happens, for example, when we try to insert a new
            // fruit but the name is already in the database
            if (throwable instanceof CompositeException) {
                throwable = throwable.getCause();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", throwable.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", throwable.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }
    }
}