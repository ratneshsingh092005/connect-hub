package com.ratnesh.connecthub.postservice.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.openfeign.support.FeignHttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

public class FeignMultipartSupportConfig {

    @Bean
    public Encoder feignFormEncoder(ObjectProvider<FeignHttpMessageConverters> feignConverters) {
        return new SpringFormEncoder(new SpringEncoder(feignConverters));
    }
}
