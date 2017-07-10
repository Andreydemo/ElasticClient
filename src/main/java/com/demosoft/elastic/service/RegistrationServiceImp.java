package com.demosoft.elastic.service;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import com.demosoft.elastic.akka.GreetingActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static com.demosoft.elastic.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
public class RegistrationServiceImp implements RegistrationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ActorSystem actorSystem;

    private ActorRef regger;
    private Timeout timeout;
    private FiniteDuration duration;

    private void init(){
        String greetingActor = "RegistrationServiceActor";
        regger = createActor(greetingActor);
        duration = FiniteDuration.create(5, TimeUnit.SECONDS);
        timeout = Timeout.durationToTimeout(duration);
        logger.info("Inited " + this);
    }

    @Override
    public String register(RegistrationMessage user) throws Exception {
        //regger = createActor(greetingActor);
        duration = FiniteDuration.create(5, TimeUnit.SECONDS);
        timeout = Timeout.durationToTimeout(duration);
        Future<Object> result = akka.pattern.Patterns.ask(regger, new GreetingActor.Greet("John"), timeout);
        return Await.result(result, duration).toString();
    }

    @Override
    public String getUser(GetUserMessage getUserMessage) {
        return null;
    }

    private ActorRef createActor(String greetingActor) {
        return actorSystem.actorOf(getProps(greetingActor), "reg");
    }
    private Props getProps(String greetingActor) {
        return SPRING_EXTENSION_PROVIDER.get(actorSystem).props(greetingActor);
    }

}
