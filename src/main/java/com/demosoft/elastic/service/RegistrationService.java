package com.demosoft.elastic.service;

/**
 * Created by andrii_korkoshko on 10.07.17.
 */
public interface RegistrationService {
    String register(RegistrationMessage registrationMessage) throws Exception;

    String getUser(GetUserMessage getUserMessage);


    class RegistrationMessage {
        public String user;
    }
    class GetUserMessage {
        public Integer id;
    }


}
