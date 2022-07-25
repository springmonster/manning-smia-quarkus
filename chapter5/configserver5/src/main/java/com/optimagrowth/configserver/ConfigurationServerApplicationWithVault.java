package com.optimagrowth.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationServerApplicationWithVault {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServerApplicationWithVault.class, args);
    }

}
