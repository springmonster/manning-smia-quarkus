package com.example;

import com.example.entity.License;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("v1/organization/{organizationId}/license")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LicensingService5Resource {

    @GET
    @Path("{licenseId}")
    public Uni<License> getLicense(@PathParam("licenseId") String licenseId, @PathParam("organizationId") String organizationId) {
        return Panache
                .withTransaction(() -> License.<License>find(
                                "SELECT id, licenseId, description, organizationId, productName, licenseType, comment FROM License WHERE licenseId = :licenseId and organizationId = :organizationId",
                                Parameters.with("licenseId", licenseId).and("organizationId", organizationId))
                        .firstResult());
    }

    @GET
    public Uni<List<License>> getAllLicenses() {
        return License.listAll();
    }
}