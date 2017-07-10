package com.demosoft.elastic.infra.akka.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutowireActorRef {

    String systemName() default "";

    @AliasFor("actorName")
    String value() default "";

    @AliasFor("value")
    String actorName() default "";

}
