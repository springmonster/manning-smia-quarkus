package com.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ApplicationResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{firstName}")
    public String helloGet(@PathParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
        return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String helloPost(HelloRequest helloRequest) {
        return String.format("{\"message\":\"Hello %s %s\"}", helloRequest.getFirstName(), helloRequest.getLastName());
    }

}