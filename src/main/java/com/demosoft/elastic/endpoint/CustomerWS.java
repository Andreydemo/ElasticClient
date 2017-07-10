package com.demosoft.elastic.endpoint;

import com.demosoft.elastic.entity.CreateCustomerRequest;
import com.demosoft.elastic.entity.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
@Path("/customer")
@Component
public class CustomerWS {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse createCustomer(CreateCustomerRequest request) {
        logger.info("first:" + request.getFirstName() + ", last:" + request.getLastName());
        return SimpleResponse.builder().message("Success").build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleResponse defaultGet() {
        return SimpleResponse.builder().message("Please use /customer/{id}").build();
    }
}
