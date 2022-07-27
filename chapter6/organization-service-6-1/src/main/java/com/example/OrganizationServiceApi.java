package com.example;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;

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
import java.util.UUID;

@Path("v1/organization")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationServiceApi {

    @GET
    @Path("{organizationId}")
    public Uni<Organization> getOrganization(@PathParam("organizationId") String organizationId) {
        return Panache.withTransaction(() ->
                Organization.<Organization>find("SELECT id, name, contactName, contactEmail, contactPhone FROM Organization WHERE id = :organizationId",
                        Parameters.with("organizationId", organizationId)).firstResult());
    }

    @GET
    public Uni<Response> getOrganizations() {
        return Panache.withTransaction(() ->
                Organization.<Organization>listAll()).map(organizations -> Response.ok(organizations).build());
    }

    @POST
    public Uni<Response> saveOrganization(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        return Panache.withTransaction(() ->
                        Organization.persist(organization))
                .replaceWith(Response.status(Response.Status.CREATED).build());
    }

    @DELETE
    @Path("{organizationId}")
    public Uni<Response> deleteOrganization(@PathParam("organizationId") String organizationId) {
        return Panache.withTransaction(() ->
                        Organization.deleteById(organizationId))
                .onItem().ifNotNull().transform(item -> item ? Response.ok().build() : Response.noContent().build());
    }

    @PUT
    @Path("{organizationId}")
    public Uni<Response> updateOrganization(@PathParam("organizationId") String organizationId) {
        return Panache.withTransaction(() ->
                        Organization.update("UPDATE Organization SET contactEmail = :contactEmail WHERE id = :id", Parameters.with("contactEmail", "xxx@qq.com").and("id", organizationId)))
                .onItem().transform(item -> Response.ok(item).build());
    }
}