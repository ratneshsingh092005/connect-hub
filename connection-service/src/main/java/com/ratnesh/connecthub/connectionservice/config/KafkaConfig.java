package com.ratnesh.connecthub.connectionservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class KafkaConfig {

    @Bean
    public NewTopic connectionRequestAccepted(){
        return new NewTopic("connection-request-accepted",3, (short) 1);
    }

    @Bean
    public NewTopic connectionRequestSent(){
        return new NewTopic("connection-request-sent",3, (short) 1);
    }
}
