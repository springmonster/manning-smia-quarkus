package com.example;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "stork://organization-service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface MyService {

    @GET
    @Path("v1/organization/{organizationId}")
    Uni<Organization> getOrganization(@PathParam("organizationId") String organizationId);
}
