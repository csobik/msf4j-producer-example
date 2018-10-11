package com.mallgroup.producer;

import com.mallgroup.lib.ReqInterceptor;
import org.springframework.context.ConfigurableApplicationContext;
import org.wso2.msf4j.spring.MSF4JSpringApplication;
import org.wso2.msf4j.spring.SpringMicroservicesRunner;

public class Application {

    public static void main(String[] args) {
//        MSF4JSpringApplication application = new MSF4JSpringApplication(Application.class);
//        application.addService()
        ConfigurableApplicationContext context = MSF4JSpringApplication.run(Application.class, args);
        context.getBean(SpringMicroservicesRunner.class).addGlobalRequestInterceptor(new ReqInterceptor());
        //.run(Application.class, args);


        //msf4JSpringApplication.addBeanFactoryPostProcessor();
//        MSF4JSpringApplication msf4JSpringApplication = new MSF4JSpringApplication(ProductResource.class);
//        ConfigurableApplicationContext configurableApplicationContext =
//                MSF4JSpringApplication.run(ProductResource.class, "--http.port=8090");
//        msf4JSpringApplication.addService(configurableApplicationContext, ProductResource.class,
//                "/reception-service")
//                .addGlobalRequestInterceptor(new CustomJWTClaimsFeature());
    }
}