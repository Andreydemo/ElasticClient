package com.demosoft.elastic.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */


@Data
@Builder
public class Customer {

    @Id
    private  String id;

    private String firstName;

    private String lastName;
}
