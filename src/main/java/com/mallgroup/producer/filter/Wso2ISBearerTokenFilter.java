package com.mallgroup.producer.filter;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.bouncycastle.util.io.pem.PemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.interceptor.RequestInterceptor;
import org.wso2.msf4j.security.JWTSecurityInterceptor;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

/**
 *
 */
@Component
@Priority(Priorities.AUTHENTICATION)
public class Wso2ISBearerTokenFilter implements RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JWTSecurityInterceptor.class);

    private static final String JWT_HEADER = "X-JWT-Assertion";
    private static final String AUTH_TYPE_JWT = "JWT";
    private String publicKeyPath = "wso2carbon.pub";

    @Context
    private HttpServletRequest httpServletRequest;

    @Override
    public boolean interceptRequest(Request request, org.wso2.msf4j.Response response) throws Exception {
        if (!doFilter(request.getHeader(JWT_HEADER))) {
            response.setHeader(javax.ws.rs.core.HttpHeaders.WWW_AUTHENTICATE, AUTH_TYPE_JWT);
            response.setStatus(javax.ws.rs.core.Response.Status.UNAUTHORIZED.getStatusCode());
            return false;
        }
        return true;
    }

    private boolean doFilter(String jwtHeader) throws IOException {

        log.info("Authentication precall");
        boolean isValidSignature;
        if (jwtHeader != null) {
            isValidSignature = verifySignature(jwtHeader);
            if (isValidSignature) {
                return true;
            }
        }

        return false;
    }

    private boolean verifySignature(String jwt) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(jwt);
            if (new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime())) {
                JWSVerifier verifier =
                        new RSASSAVerifier((RSAPublicKey) getPublicKey());
                return signedJWT.verify(verifier);
            } else {
                log.info("Token has expired");
            }
        } catch (Exception e) {
            log.error("Error occurred while JWT signature verification. JWT=" + jwt, e);
        }
        return false;
    }

    private PublicKey getPublicKey() {
        try {
            // !!! cache it
            File file = new File(getClass().getClassLoader().getResource(publicKeyPath).getFile());

            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            final PemReader reader = new PemReader( new FileReader(file) );
            final byte[] pubKey = reader.readPemObject().getContent();
            final X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pubKey);
            return keyFactory.generatePublic( publicKeySpec );
        }
        catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
