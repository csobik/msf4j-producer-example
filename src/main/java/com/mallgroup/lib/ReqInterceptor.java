package com.mallgroup.lib;

import org.wso2.msf4j.Request;
import org.wso2.msf4j.Response;
import org.wso2.msf4j.interceptor.RequestInterceptor;

public class ReqInterceptor implements RequestInterceptor {

    @Override
    public boolean interceptRequest(Request request, Response response) throws Exception {
        System.out.println(request.getUri());
        return false;
    }
}
