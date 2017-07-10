package com.demosoft.elastic.endpoint;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import com.demosoft.elastic.akka.GreetingActor;
import com.demosoft.elastic.infra.akka.annotation.AutowireActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @AutowireActorRef("greetingActor")
    private ActorRef greeter;

    private Timeout timeout;
    private FiniteDuration duration;

    public AkkaTest() {
        logger.info("Constructed " + this);
    }

    @PostConstruct
    void init() {
        duration = FiniteDuration.create(5, TimeUnit.SECONDS);
        timeout = Timeout.durationToTimeout(duration);
    }

    @GET
    public String test() throws Exception {
        logger.info("Used " + this);
        Future<Object> result = akka.pattern.Patterns. ask(greeter, new GreetingActor.Greet("John"), timeout);
        return Await.result(result, duration).toString();
    }

    @GET
    @Path("/{login}")
    public String reg(@PathParam("login") String login) throws Exception {
        logger.info("Used " + this);
        Future<Object> result = akka.pattern.Patterns.ask(greeter, new GreetingActor.Greet("John"), timeout);
        return Await.result(result, duration).toString();
    }
}
