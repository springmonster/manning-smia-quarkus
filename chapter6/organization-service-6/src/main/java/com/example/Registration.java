package com.example;

import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.consul.ConsulClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class Registration {

    @ConfigProperty(name = "consul.host")
    String host;
    @ConfigProperty(name = "consul.port")
    int port;

    @ConfigProperty(name = "organization-service-port-a", defaultValue = "9000")
    int portA;


    @ConfigProperty(name = "organization-service-port-b", defaultValue = "9001")
    int portB;

    /**
     * Register our two services in Consul.
     * <p>
     * Note: this method is called on a worker thread, and so it is allowed to block.
     */
    public void init(@Observes StartupEvent ev, Vertx vertx) {
        ConsulClient client = ConsulClient.create(vertx, new ConsulClientOptions().setHost(host).setPort(port));

        client.registerServiceAndAwait(
                new ServiceOptions().setPort(portA).setAddress("localhost").setName("organization-service").setId("organization-service-a"));

        client.registerServiceAndAwait(
                new ServiceOptions().setPort(portB).setAddress("localhost").setName("organization-service").setId("organization-service-b"));
    }
}
