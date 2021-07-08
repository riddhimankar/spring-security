package com.saral.accountService.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;

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
        return JwtDecoderFactory.getDecoder(jwtDecoder, secretKey, publicKey, jksUri);
    }



}
