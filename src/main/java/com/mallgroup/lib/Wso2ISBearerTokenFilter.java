package com.mallgroup.lib;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * @author Vlastimil Hajek
 */
@Priority(Priorities.AUTHENTICATION)
public class Wso2ISBearerTokenFilter implements ContainerRequestFilter {

    private final static String JWKS_PATH = "/oauth2/jwks";

    private final static String AUTH_SCHEME = "OAUTH_BEARER";

//    // save jwk source in filter - keys are cached in source
//    private final JWKSource jwkSource;
//
//    @Context
//    private HttpServletRequest httpServletRequest;

//    public Wso2ISBearerTokenFilter(final String authServerUrl) {
//        this.jwkSource = getJWKSource(authServerUrl);
//    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        System.out.println(requestContext.getHeaders());

//        try {
//            // Hardcode the RSA key
//            String modulusString = "jH80rhwWEymDl4hY_WlSAIJdQA4gZZJ1XAEn1JKmkuKUODq0QKqtMtS_w50fAXJ9tmykgW4AoZ8v12Z5KxbZLHjEIw7kXLgPI9FAFZcLsk94EAL5iuuhVqDziHHV95KyNB8YAY-77lRBtcdw-CuF8qcoiYzrMWtcrr-tGfBQGxm5Q-kgUM-zqDULBp9ZSl4slfz8ufEFITZaqrpp7clkPwNqvkDzMO-cZ-sVA01X2iHDC_pBe83dFy6eDVArw3aDtSQ5Tk0x50F8mnuTh-EzrL8cyI5OafRnIeAErHwn2smSLy0CRD-1w2E_C1bbHoYt2Me3G_OMoC2JmvyU8fzSVw";
//            String publicExponentString = "AQAB";
//
//            BigInteger modulus = new BigInteger(1, Base64Url.decode(modulusString));
//            BigInteger pubExponent = new BigInteger(1, Base64Url.decode(publicExponentString));
//
//            // Create private and public key specs
//            RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(modulus, pubExponent);
//
//            // Create a key factory
//            KeyFactory factory = KeyFactory.getInstance("RSA");
//
//            // Create the RSA private and public keys
//            RSAPublicKey pubKey = (RSAPublicKey) factory.generatePublic(publicSpec);

//            try {

//                // Make the OAuth Request out of this request and validate it
//                // Specify where you expect OAuth access token (request header, body or query string)
//                OAuthAccessResourceRequest oauthRequest = new
//                        OAuthAccessResourceRequest(httpServletRequest, TokenType.BEARER);
//
//                // Get the access token
//                String accessToken = oauthRequest.getAccessToken();
//
//
//        // Get the access token
//        final String accessToken = getAccessToken(httpServletRequest)
//                .orElseThrow(() -> authError("No token found"));
//
//        //... parse access token
//        final SignedJWT signedJWT;
//        try {
//            signedJWT = SignedJWT.parse(accessToken);
//        } catch (ParseException e) {
//            throw authError("Access token couldn't be parsed to a valid signed JWT", e);
//        }
//
//        //... validate access token
//        final JWSAlgorithm algorithm = signedJWT.getHeader().getAlgorithm();
//        final ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
//        final JWSKeySelector keySelector = new JWSVerificationKeySelector(algorithm, jwkSource);
//        jwtProcessor.setJWSKeySelector(keySelector);
//
//        final JWTClaimsSet jwtClaimsSet;
//        try {
//            jwtClaimsSet = jwtProcessor.process(signedJWT, null);
//        } catch (BadJOSEException | JOSEException e) {
//            throw authError("Process JWT failed, bad payload or invalid signature", e);
//        }
//
////                //... validate access token
////                SignedJWT signedJWT = SignedJWT.parse(accessToken);
////                final JWSHeader header = signedJWT.getHeader();
////                final JWSAlgorithm algorithm = header.getAlgorithm();
////                // Set up a JWT processor to parse the tokens and then check their signature
////// and validity time window (bounded by the "iat", "nbf" and "exp" claims)
////                ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
////// The expected JWS algorithm of the access tokens (agreed out-of-band)
////// Configure the JWT processor with a key selector to feed matching public
////// RSA keys sourced from the JWK set URL
////                // save key selector in filter - remote keys are cached in key selector
////                JWSKeySelector keySelector = new JWSVerificationKeySelector(algorithm, jwkSource);
////                jwtProcessor.setJWSKeySelector(keySelector);
////                final JWTClaimsSet jwtClaimsSet;
////                try {
////                    jwtClaimsSet = jwtProcessor.process(signedJWT, null);
////                } catch (BadJOSEException e) {
////                    // todo
////                    throw new RuntimeException(e);
////                }
//////                JWSVerifier verifier = new RSASSAVerifier(pubKey);
//////
//////                if (signedJWT.verify(verifier)) {
//////                    log.debug("JWT signature was successfully verified.");
//////
//////                } else {
//////                    log.debug("JWT signature was NOT valid.");
//////                    authError();
//////                }
//////                final JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
//
//
//        final SecurityContext securityContext = createSecurityContext(jwtClaimsSet, requestContext);
//
////                final String subject = jwtClaimsSet.getSubject();
////
////                final MgmPrincipal principal = new MgmPrincipal(subject);
////
////                // todo roles
////                final Collection<String> roles = Collections.emptyList();
////
////                final SecurityContext anonymousSecurityContext = requestContext.getSecurityContext();
////                final boolean isSecure = anonymousSecurityContext.isSecure();
////
////                SecurityContext ctx = new SecurityContext() {
////                    @Override
////                    public Principal getUserPrincipal() {
////                        return principal;
////                    }
////
////                    @Override
////                    public boolean isUserInRole(String role) {
////                        return roles.contains(role);
////                    }
////
////                    @Override
////                    public boolean isSecure() {
////                        return isSecure;
////                    }
////
////                    @Override
////                    public String getAuthenticationScheme() {
////                        return "OAUTH_BEARER";
////                    }
////                };
//
//
//        requestContext.setSecurityContext(securityContext);
//
//
////            } catch (OAuthProblemException ex) {
////                authError();
////            }
////
////        } catch (OAuthSystemException | ParseException | NoSuchAlgorithmException | InvalidKeySpecException | JOSEException e) {
////            e.printStackTrace();
////        }
//
//
//    }
//
//
//    private JWKSource getJWKSource(String authServerUrl) {
//        // The public RSA keys to validate the signatures will be sourced from the
//        // OAuth 2.0 server's JWK set, published at a well-known URL. The RemoteJWKSet
//        // object caches the retrieved keys to speed up subsequent look-ups and can
//        // also gracefully handle key-rollover
//        if (authServerUrl == null) {
//            throw new IllegalArgumentException("Auth server url must be not null.");
//        }
//
//        try {
//            return new RemoteJWKSet(new URL(new URL(authServerUrl), JWKS_PATH));
//        } catch (MalformedURLException e) {
//            throw new IllegalArgumentException("Illegal auth server's JWK set URL, " +
//                    "authServerUrl=" + authServerUrl + ", JWKS_PATH=" + JWKS_PATH);
//        }
//    }
//
//
//    private Optional<String> getAccessToken(HttpServletRequest httpServletRequest) {
//        final String accessToken;
//        try {
//            final OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(httpServletRequest, TokenType.BEARER);
//            accessToken = oauthRequest.getAccessToken();
//        } catch (OAuthSystemException | OAuthProblemException e) {
//            // Failed to obtain token
//            throw authError("Failed to obtain token", e);
//        }
//        return Optional.ofNullable(accessToken);
//    }
//
//    private SecurityContext createSecurityContext(JWTClaimsSet jwtClaimsSet, ContainerRequestContext requestContext) {
//        final String subject = jwtClaimsSet.getSubject();
//
//        final MgmPrincipal principal = new MgmPrincipal(subject);
//
//        // todo roles
//        final Collection<String> roles;
//        final String username = subject.split("@")[0];
//        final String[] split = username.split("_");
//        if (split.length > 1) {
//            roles = Arrays.asList(Arrays.copyOfRange(split, 1, split.length));
//        } else {
//            roles = Collections.emptyList();
//        }
//
//        final SecurityContext anonymousSecurityContext = requestContext.getSecurityContext();
//        final boolean isSecure = anonymousSecurityContext.isSecure();
//
//        return new SecurityContext() {
//            @Override
//            public Principal getUserPrincipal() {
//                return principal;
//            }
//
//            @Override
//            public boolean isUserInRole(String role) {
//                return roles.contains(role);
//            }
//
//            @Override
//            public boolean isSecure() {
//                return isSecure;
//            }
//
//            @Override
//            public String getAuthenticationScheme() {
//                return AUTH_SCHEME;
//            }
//        };
//    }
//
//    private WebApplicationException authError(String message, Throwable cause) {
//        final String msg = "Authentication failed: " + message + ".";
//        final Response.Status status = Response.Status.UNAUTHORIZED;
//        if (cause != null) {
//            return new WebApplicationException(msg, cause, status);
//        } else {
//            return new WebApplicationException(msg, status);
//        }
//    }
//
//
//    private WebApplicationException authError(String message) {
//        return authError(message, null);
//    }
//
//    private class MgmPrincipal implements Principal {
//
//        private final String subject;
//
//        MgmPrincipal(String subject) {
//            this.subject = subject;
//        }
//
//        @Override
//        public String getName() {
//            return subject;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (o == null || getClass() != o.getClass()) {
//                return false;
//            }
//            final MgmPrincipal that = (MgmPrincipal) o;
//            return Objects.equals(subject, that.subject);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(subject);
//        }
    }
}
