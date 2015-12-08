package com.kafka.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.kafka.rest.endpoint.ResponseCorsFilter;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

public class KafkaApplicationSetup extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {

                super.configureServlets();

                // Configuring Jersey via Guice:
                ResourceConfig resourceConfig = new PackagesResourceConfig("com/kafka/rest");
                for (Class<?> resource : resourceConfig.getClasses()) {
                    bind(resource);
                }

                // hook Jackson into Jersey as the POJO <-> JSON mapper
                bind(MyJacksonJsonProvider.class).in(Scopes.SINGLETON);

                serve("/kafka/*").with(GuiceContainer.class);

                filter("/kafka/*").through(ResponseCorsFilter.class);
            }
        }, new UserModule());
    }
}
