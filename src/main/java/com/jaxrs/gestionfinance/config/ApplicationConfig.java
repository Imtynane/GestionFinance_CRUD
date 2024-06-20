package com.jaxrs.gestionfinance.config;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        packages("com.jaxrs.gestionfinance.resources");
    }
}
