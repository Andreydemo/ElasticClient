package com.demosoft.elastic.service;

import com.demosoft.elastic.entity.Customer;
import com.demosoft.elastic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
@Component
public class MongoCustomerManagementService implements CustomerManagementService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.insert(customer);
    }
}
