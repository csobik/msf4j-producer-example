package com.mallgroup.producer;

import com.mallgroup.producer.filter.ReqInterceptor;
import com.mallgroup.producer.filter.Wso2ISBearerTokenFilter;
import org.springframework.context.ConfigurableApplicationContext;
import org.wso2.msf4j.spring.MSF4JSpringApplication;
import org.wso2.msf4j.spring.SpringMicroservicesRunner;

public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = MSF4JSpringApplication.run(Application.class, args);
        context.getBean(SpringMicroservicesRunner.class)
                .addGlobalRequestInterceptor(
                        new ReqInterceptor(),
                        new Wso2ISBearerTokenFilter());
    }
}