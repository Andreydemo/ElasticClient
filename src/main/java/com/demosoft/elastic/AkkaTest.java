package com.demosoft.elastic;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.demosoft.elastic.akka.GreetingActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.TimeUnit;

import static com.demosoft.elastic.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * Created by admin on 7/7/17.
 */
@Component
@Path("/akka")
public class AkkaTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ActorSystem actorSystem;
    private ActorRef greeter;
    private Timeout timeout;
    private FiniteDuration duration;

    public AkkaTest() {
        logger.info("Constructed " + this);
    }

    @PostConstruct
    void init() {
        greeter = actorSystem.actorOf(SPRING_EXTENSION_PROVIDER.get(actorSystem).props("greetingActor"), "greeter");
        duration = FiniteDuration.create(5, TimeUnit.SECONDS);
        timeout = Timeout.durationToTimeout(duration);
        logger.info("Inited " + this);
    }

    @GET
    public String test() throws Exception {
        logger.info("Used " + this);
        Future<Object> result = akka.pattern.Patterns.ask(greeter, new GreetingActor.Greet("John"), timeout);
        return Await.result(result, duration).toString();
    }
}
