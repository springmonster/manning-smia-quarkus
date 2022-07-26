package com.example;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloResource {

    @ConfigProperty(name = "message", defaultValue = "hello default")
    String message;

    @GET
    public String hello() {
        return message;
    }
}