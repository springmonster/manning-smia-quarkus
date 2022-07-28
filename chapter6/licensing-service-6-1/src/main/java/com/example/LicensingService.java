package com.example;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.PathParam;

@ApplicationScoped
public class LicensingService {

    @RestClient
    MyService myService;

    public Uni<License> getLicense(@PathParam("organizationId") String organizationId, @PathParam("licenseId") String licenseId) {
        return Panache.withTransaction(() ->
                License.getByOrganizationIdAndLicenseId(organizationId, licenseId));
    }

    public Uni<Organization> getOrganization(String organizationId) {
        return myService.getOrganization(organizationId);
    }
}
