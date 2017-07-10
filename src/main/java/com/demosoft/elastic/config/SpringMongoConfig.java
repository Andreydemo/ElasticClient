package com.demosoft.elastic.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.Arrays;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "customer_managment";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        ServerAddress host = new ServerAddress("ds153682.mlab.com", 53682);
        MongoCredential credential = MongoCredential.createCredential("admin1", "customer_managment", "admin1".toCharArray());
        return new MongoClient(host, Arrays.asList(credential));
    }
}