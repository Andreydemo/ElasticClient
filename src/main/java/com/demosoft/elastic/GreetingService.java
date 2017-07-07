package com.demosoft.elastic;

import org.springframework.stereotype.Component;

/**
 * Created by admin on 7/7/17.
 */
@Component
public class GreetingService {

    public String greet(String name) {
        return "Hello, " + name;
    }
}
