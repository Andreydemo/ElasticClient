package com.demosoft.elastic.endpoint;

/**
 * Created by admin on 7/3/17.
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.demosoft.elastic.Service;
import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class Endpoint {

    private final Service service;

    public Endpoint(Service service) {
        this.service = service;
    }

    @GET
    public String message() {
        return "Hello " + this.service.message();
    }

}