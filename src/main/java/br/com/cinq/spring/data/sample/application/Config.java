package br.com.cinq.spring.data.sample.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.sample.resource.CityResource;

/**
 * Register Jersey modules
 * @author Adriano Kretschmer
 */
//@Configuration
@Component
@ApplicationPath("/")
public class Config extends ResourceConfig {

    public Config() {
        register(CityResource.class);
        packages("br.com.cinq.spring.data.sample.resource");
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }


}