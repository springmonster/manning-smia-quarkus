package com.example;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Parameters;
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
                License.<License>find("SELECT licenseId, description, organizationId, productName, licenseType, comment FROM License WHERE organizationId = :organizationId and licenseId = :licenseId", Parameters.with("organizationId", organizationId).and("licenseId", licenseId)).firstResult());

//        Uni<Organization> organization = myService.getOrganization(organizationId);
//
//        organization.invoke(organization12 -> logger.info("organization info {}", organization12.toString()));
//
//        licenseUni.invoke(license -> {
//            organization.invoke(organization1 -> {
//                license.setOrganizationName(organization1.getName());
//                license.setContactName(organization1.getContactName());
//                license.setContactEmail(organization1.getContactEmail());
//                license.setContactPhone(organization1.getContactPhone());
//            });
//        });
//
//        return licenseUni;
    }
}
