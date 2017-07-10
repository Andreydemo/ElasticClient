package com.demosoft.elastic.service;

import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
public class RegistrationServiceActor extends UntypedActor implements RegistrationService {

    private Map<Integer, String> repo = new HashMap<Integer, String>();

    @Override
    public String register(RegistrationMessage registrationMessage) throws Exception {
        return null;
    }

    @Override
    public String getUser(GetUserMessage getUserMessage) {
        return null;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof RegistrationMessage){
            register((RegistrationMessage) message);
        }
        if(message instanceof GetUserMessage){
            getUser((GetUserMessage) message);
        }
    }
}
