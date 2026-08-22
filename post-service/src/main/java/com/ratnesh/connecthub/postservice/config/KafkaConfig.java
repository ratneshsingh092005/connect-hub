package com.ratnesh.connecthub.postservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic postCreated() {
        return new NewTopic(
                "post-created",
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic postLiked() {
        return new NewTopic(
                "post-liked",
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic postReposted() {
        return new NewTopic(
                "post-reposted",
                3,
                (short) 1
        );
    }
}