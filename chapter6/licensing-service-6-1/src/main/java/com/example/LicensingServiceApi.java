package com.example;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * A frontend API using our REST Client (which uses Stork to locate and select the service instance on each call).
 */
@Path("v1/organization")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LicensingServiceApi {

    @RestClient
    MyService myService;

    @Inject
    private LicensingService licensingService;

    @GET
    @Path("{organizationId}/license/{licenseId}")
    public Uni<License> getLicense(@PathParam("organizationId") String organizationId, @PathParam("licenseId") String licenseId) {
        return licensingService.getLicense(organizationId, licenseId);
    }

    @GET
    @Path("{organizationId}/license")
    public Uni<List<License>> getLicensesInOrganization(@PathParam("organizationId") String organizationId) {
        return Panache.withTransaction(() ->
                License.find("SELECT licenseId, organizationId, description, licenseType, productName, comment FROM License WHERE organizationId = :organizationId", Parameters.with("organizationId", organizationId)).list());
    }

    @GET
    public Uni<List<License>> getLicenses() {
        return Panache.withTransaction(() ->
                License.<License>listAll());
    }

//    @GET
//    @Path("{organizationId}")
//    public String getOrganizationText(@PathParam("organizationId") String organizationId) {
//        return myService.getOrganizationText(organizationId);
//    }

    @GET
    @Path("{organizationId}")
    public Uni<Organization> getOrganization(@PathParam("organizationId") String organizationId) {
        return myService.getOrganization(organizationId);
    }
}
