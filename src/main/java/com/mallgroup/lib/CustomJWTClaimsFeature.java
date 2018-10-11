package com.mallgroup.lib;

import org.springframework.stereotype.Component;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.Response;
import org.wso2.msf4j.interceptor.RequestInterceptor;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Interceptor for handling custom JWT claims.
 */
@Provider
public class CustomJWTClaimsFeature implements DynamicFeature {
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        String header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        System.out.println(header);
//    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        context.register(new Wso2ISBearerTokenFilter());
    }

    //
//    private static final String JWT_HEADER = "X-JWT-Assertion";
//    private static final String AUTH_TYPE_JWT = "JWT";
//
//    @Override
//    public boolean interceptRequest(Request request, Response response) throws Exception {
//        HttpHeaders headers = request.getHeaders();
//        if (headers != null) {
//            String jwtHeader = headers.getHeaderString(JWT_HEADER);
//            if (jwtHeader != null) {
//                SignedJWT signedJWT = SignedJWT.parse(jwtHeader);
//                ReadOnlyJWTClaimsSet readOnlyJWTClaimsSet = signedJWT.getJWTClaimsSet();
//                if (readOnlyJWTClaimsSet != null) {
//                    // Do something with claims
//                    return true;
//                }
//            }
//        }
//        response.setHeader(javax.ws.rs.core.HttpHeaders.WWW_AUTHENTICATE, AUTH_TYPE_JWT);
//        response.setStatus(javax.ws.rs.core.Response.Status.UNAUTHORIZED.getStatusCode());
//        return false;
//    }
}
