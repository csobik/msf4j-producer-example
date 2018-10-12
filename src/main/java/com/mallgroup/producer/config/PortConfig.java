package com.mallgroup.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.wso2.msf4j.spring.transport.HTTPTransportConfig;

@Configuration
public class PortConfig {

    @Bean
    public HTTPTransportConfig http() {
        //System.out.println(System.getProperties());
        int port = StringUtils.isEmpty(System.getProperty("server.port")) ? 8080 : Integer.parseInt(System.getProperty("server.port"));
        return new HTTPTransportConfig(port);
    }
}