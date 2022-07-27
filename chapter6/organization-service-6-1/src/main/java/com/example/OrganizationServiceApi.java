package com.example;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/organization")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationServiceApi {

    @Inject
    OrganizationService organizationService;

    @GET
    @Path("{organizationId}")
    public Uni<Organization> getOrganization(@PathParam("organizationId") String organizationId) {
        return organizationService.getOrganization(organizationId);
    }

    @GET
    public Uni<Response> getOrganizations() {
        return organizationService.getOrganizations();
    }

    @POST
    public Uni<Response> saveOrganization(Organization organization) {
        return organizationService.saveOrganization(organization);
    }

    @DELETE
    @Path("{organizationId}")
    public Uni<Response> deleteOrganization(@PathParam("organizationId") String organizationId) {
        return organizationService.deleteOrganization(organizationId);
    }

    @PUT
    @Path("{organizationId}")
    public Uni<Response> updateOrganization(@PathParam("organizationId") String organizationId) {
        return organizationService.updateOrganization(organizationId);
    }
}