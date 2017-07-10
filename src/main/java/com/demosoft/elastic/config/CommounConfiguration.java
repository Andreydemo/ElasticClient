package com.demosoft.elastic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
@Component
public class CommounConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        return builder;
    }
}
