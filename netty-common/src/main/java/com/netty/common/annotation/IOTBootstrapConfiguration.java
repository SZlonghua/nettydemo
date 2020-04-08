package com.netty.common.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOTBootstrapConfiguration {

    @Bean
    public IOTListenerAnnotationRegistry iotListenerAnnotationRegistry(){
        return new IOTListenerAnnotationRegistry();
    }
}
