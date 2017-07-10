package com.demosoft.elastic.infra.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.demosoft.elastic.infra.ReflectionUtils;
import com.demosoft.elastic.infra.akka.annotation.AutowireActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

import static com.demosoft.elastic.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
@Component
public class CreateActorRefBeanPostProcessor implements BeanPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        List<Field> fields = ReflectionUtils.getAllFields(bean.getClass());
        fields.forEach(field -> {
            if (field.isAnnotationPresent(AutowireActorRef.class)) {
                AutowireActorRef annotation = field.getAnnotation(AutowireActorRef.class);

                injectObject(bean, field, annotation);
            }
        });
        return bean;
    }

    private void injectObject(Object bean, Field field, AutowireActorRef annotation) {
        String systemName = annotation.systemName();
        ActorSystem actorSystem = null;
        if (StringUtils.isEmpty(actorSystem)) {
            actorSystem = getDefaultActorSystem(systemName);
        } else {
            actorSystem = (ActorSystem) context.getBean(systemName);
            if (actorSystem == null) actorSystem = getDefaultActorSystem(systemName);
        }
        ActorRef actor = createActor(actorSystem, annotation.value());

        ReflectionUtils.setField(field, bean, actor);
    }

    private ActorSystem getDefaultActorSystem(String systemName) {
        logger.warn("Actor system [" + systemName + "] not found, default has been used");
        return context.getBean(ActorSystem.class);
    }

    private ActorRef createActor(ActorSystem actorSystem, String actorName) {
        return actorSystem.actorOf(getProps(actorSystem,actorName), actorName);
    }

    private Props getProps(ActorSystem actorSystem,String greetingActor) {
        return SPRING_EXTENSION_PROVIDER.get(actorSystem).props(greetingActor);
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}
