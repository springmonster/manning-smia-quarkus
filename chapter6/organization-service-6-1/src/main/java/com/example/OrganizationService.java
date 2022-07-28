package com.example;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.UUID;

@ApplicationScoped
public class OrganizationService {

    public Uni<Response> getOrganization(String organizationId) {
        return Panache.withTransaction(() -> Organization.findById(organizationId))
                .onItem().transform(organization -> Response.ok(organization).build());
    }

    public Uni<Response> getOrganizations() {
        return Panache.withTransaction(() ->
                Organization.<Organization>listAll()).map(organizations -> Response.ok(organizations).build());
    }

    public Uni<Response> saveOrganization(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        return Panache.withTransaction(() ->
                        Organization.persist(organization))
                .replaceWith(Response.status(Response.Status.CREATED).build());
    }

    public Uni<Response> deleteOrganization(String organizationId) {
        return Panache.withTransaction(() ->
                        Organization.deleteById(organizationId))
                .onItem().ifNotNull().transform(item -> item ? Response.ok().build() : Response.noContent().build());
    }

    public Uni<Response> updateOrganization(String organizationId) {
        return Panache.withTransaction(() ->
                        Organization.update("UPDATE Organization SET contactEmail = :contactEmail WHERE id = :id", Parameters.with("contactEmail", "xxx@qq.com").and("id", organizationId)))
                .onItem().transform(item -> Response.ok(item).build());
    }
}
