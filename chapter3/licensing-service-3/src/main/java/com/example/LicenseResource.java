package com.example;

import com.example.model.License;
import com.example.service.LicenseService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/organization/{organizationId}/license")
public class LicenseResource {

    @Inject
    LicenseService licenseService;

    @GET
    @Path("{licenseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLicense(@PathParam("organizationId") String organizationId, @PathParam("licenseId") String licenseId) {
        License license = licenseService.getLicense(licenseId, organizationId);
        return Response.ok(license).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLicense(@PathParam("organizationId") String organizationId, License request,
                                  @HeaderParam(value = "Accept-Language") String lang) {
        return Response.ok(licenseService.createLicense(request, organizationId, lang)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLicense(@PathParam("organizationId") String organizationId, License request,
                                  @HeaderParam(value = "Accept-Language") String lang) {
        return Response.ok(licenseService.updateLicense(request, organizationId, lang)).build();
    }

    @DELETE
    @Path("{licenseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLicense(@PathParam("organizationId") String organizationId, @PathParam("licenseId") String licenseId, @HeaderParam(value = "Accept-Language") String lang) {
        return Response.ok(licenseService.deleteLicense(licenseId, organizationId, lang)).build();
    }
}