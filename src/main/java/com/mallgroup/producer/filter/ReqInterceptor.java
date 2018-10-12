package com.mallgroup.producer.filter;

import org.wso2.msf4j.Request;
import org.wso2.msf4j.Response;
import org.wso2.msf4j.interceptor.RequestInterceptor;

public class ReqInterceptor implements RequestInterceptor {

    @Override
    public boolean interceptRequest(Request request, Response response) throws Exception {

        // print all request headers
        request.getHeaders().getRequestHeaders().forEach((k, v) -> System.out.println(k + " => " + v));

        return true;
    }
}
