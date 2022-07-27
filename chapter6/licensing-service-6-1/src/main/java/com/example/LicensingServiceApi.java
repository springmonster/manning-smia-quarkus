package com.example;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A frontend API using our REST Client (which uses Stork to locate and select the service instance on each call).
 */
@Path("v1/organization/{organizationId}/license")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LicensingServiceApi {

    @Inject
    private LicensingService licensingService;

    @GET
    @Path("{licenseId}")
    public Uni<Response> getLicense(@PathParam("organizationId") String organizationId, @PathParam("licenseId") String licenseId) {
        return licensingService.getLicense(organizationId, licenseId);
    }

}
