package com.demosoft.elastic.endpoint;

import com.demosoft.elastic.entity.CreateCustomerRequest;
import com.demosoft.elastic.entity.Customer;
import com.demosoft.elastic.entity.SimpleResponse;
import com.demosoft.elastic.service.CustomerManagementService;
import com.demosoft.elastic.service.MongoCustomerManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomerManagementService customerManagementService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SimpleResponse createCustomer(CreateCustomerRequest request) {
        logger.info("first:" + request.getFirstName() + ", last:" + request.getLastName());
        Customer newCustomer = customerManagementService.create(Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName()).build());
        return SimpleResponse.builder().message("Success, id:" + newCustomer.getId()).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleResponse defaultGet() {
        return SimpleResponse.builder().message("Please use /customer/{id}").build();
    }
}
