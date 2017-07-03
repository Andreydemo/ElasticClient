package com.demosoft.elastic;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Andrii_Korkoshko on 7/3/2017.
 */
@SpringBootApplication
public class Launcher extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new Launcher()
                .configure(new SpringApplicationBuilder(Launcher.class))
                .run(args);
    }
}
