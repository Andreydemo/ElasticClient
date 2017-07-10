package com.demosoft.elastic.config;

import com.demosoft.elastic.endpoint.AkkaTest;
import com.demosoft.elastic.endpoint.CustomerWS;
import com.demosoft.elastic.endpoint.Endpoint;
import com.demosoft.elastic.endpoint.ReverseEndpoint;
import org.glassfish.jersey.server.ResourceConfig;

import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Endpoint.class);
        register(ReverseEndpoint.class);
        register(AkkaTest.class);
        register(CustomerWS.class);
    }

}