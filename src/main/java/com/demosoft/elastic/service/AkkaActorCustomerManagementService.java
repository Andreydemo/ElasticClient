package com.demosoft.elastic.service;

import akka.actor.AbstractActor;
import com.demosoft.elastic.entity.Customer;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by andrii_korkoshko on 11.07.17.
 */
@Component
public class AkkaActorCustomerManagementService extends AbstractActor {

    @Autowired
    @Qualifier("rootCMS")
    private CustomerManagementService customerManagementService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CreateCustomerRequest.class, s -> {
                    Customer newCustomer = customerManagementService.create(s.getCustomer());
                    getSender().tell(CreateCustomerResponse.builder().customer(newCustomer).build(), getSelf());
                })
                .match(GetCustomerRequest.class, s -> {
                    Customer customer = customerManagementService.getById(s.getId());
                    getSender().tell(GetCustomerResponse.builder().customer(customer).build(), getSelf());
                })
                .matchAny(o -> logger.info("received unknown message"))
                .build();
    }

    @Data
    @Builder
    public static class CreateCustomerRequest {
        private Customer customer;

    }

    @Data
    @Builder
    public static class CreateCustomerResponse {
        private Customer customer;
    }

    @Data
    @Builder
    public static class GetCustomerRequest {
        private String id;
    }

    @Data
    @Builder
    public static class GetCustomerResponse {
        private Customer customer;
    }
}
