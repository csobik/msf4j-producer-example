package com.mallgroup.producer.config;

import com.mallgroup.lib.CustomJWTClaimsFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wso2.msf4j.spring.transport.HTTPTransportConfig;

@Configuration
public class PortConfig {

    @Bean
    public HTTPTransportConfig http() {
        return new HTTPTransportConfig(8080);
    }

    @Bean
    public CustomJWTClaimsFeature customJWTClaimsInterceptor() {
        return new CustomJWTClaimsFeature();
    }
}