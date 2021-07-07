package com.saral.accountService.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class ServiceConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.jksUri}")
    private String jksUri;

    @Value("${security.publicKey}")
    private String publicKey;

    @Value("${security.secretKey}")
    private String secretKey;

    @Value("${security.introspectUri}")
    private String introspectUri;

    @Value("${security.client.id}")
    private String clientId;

    @Value("${security.client.secret}")
    private String clientSecret;

    @Value("${security.tokenType}")
    private String tokenType;

    @Value("${security.jwtDecoder}")
    private String jwtDecoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (tokenType.equalsIgnoreCase("opaque")){
            opaqueTokenAuthentication(http);
        }

        if (tokenType.equalsIgnoreCase("jwt")) {
            jwtTokenAuthentication(http);
        }

        http.authorizeRequests().anyRequest().authenticated();
    }

    /** Implementation of the Opaque token
     *
     * @param http
     */
    private void opaqueTokenAuthentication(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                c -> c.opaqueToken(
                        t -> {
                            t.introspectionUri(introspectUri);
                            t.introspectionClientCredentials(clientId, clientSecret);
                        }
                )
        );
    }


    /** Implementation of the JWT token
     *
     * @param http
     */
    private void jwtTokenAuthentication(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                c -> c.jwt(
                        j -> j.decoder(decoder())
                )
        );
    }

    @Bean
    public JwtDecoder decoder() {
        String key = secretKey;
        SecretKey secret = new SecretKeySpec(key.getBytes(),
                                             0,
                                             key.getBytes().length,
                                             "AES");
        switch (jwtDecoder) {
            case "secret":
                return NimbusJwtDecoder.withSecretKey(secret).build();
            case "publicKey":
                RSAPublicKey rsaKey = null;
                try {
                    rsaKey = stringToRsaKey(publicKey);
                } catch (Exception e) {
                    throw new SecurityException(
                            "Unable to create RSA Public key. " + e.getMessage());
                }

                return NimbusJwtDecoder.withPublicKey(rsaKey).build();
            case "keySet":
                return NimbusJwtDecoder.withJwkSetUri(jksUri).build();
            default:
                throw new SecurityException("JWT decoder not found. jwtDecoder: " + jwtDecoder);
        }

    }

    private RSAPublicKey stringToRsaKey(String publicKey) throws NoSuchAlgorithmException,
                                                           InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        var pubKey = Base64.getDecoder().decode(publicKey);

        var x509 = new X509EncodedKeySpec(pubKey);
        return (RSAPublicKey) keyFactory.generatePublic(x509);
    }

}
