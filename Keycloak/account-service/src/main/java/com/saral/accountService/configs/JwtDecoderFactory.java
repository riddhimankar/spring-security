package com.saral.accountService.configs;

import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.util.Base64;
import java.util.List;

public class JwtDecoderFactory {

    public static NimbusJwtDecoder getDecoder(String jwtDecoder,
                                              String secretKey,
                                              String publicKey,
                                              String jksUri,
                                              String issuerUri){


        NimbusJwtDecoder nimbusJwtDecoder =  null;
        OAuth2TokenValidator<Jwt> withClockSkew = new DelegatingOAuth2TokenValidator<>(
                new JwtTimestampValidator(Duration.ofSeconds(60)),
                new JwtIssuerValidator(issuerUri));

        OAuth2TokenValidator<Jwt> audienceValidator = audienceValidator();
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

         switch (jwtDecoder) {
            case "secret":
                String key = secretKey;
                SecretKey secret = new SecretKeySpec(key.getBytes(),
                                                     0,
                                                     key.getBytes().length,
                                                     "AES");
                nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secret).build();
                break;
            case "publicKey":
                RSAPublicKey rsaKey = null;
                try {
                    rsaKey = stringToRsaKey(publicKey);
                } catch (Exception e) {
                    throw new SecurityException(
                            "Unable to create RSA Public key. " + e.getMessage());
                }
                nimbusJwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKey).build();
                break;
            case "keySet":
                nimbusJwtDecoder = NimbusJwtDecoder.withJwkSetUri(jksUri).build();
                break;
            default:
                throw new SecurityException("JWT decoder not found. jwtDecoder: " + jwtDecoder);
        }

        nimbusJwtDecoder.setJwtValidator(withClockSkew);
        nimbusJwtDecoder.setJwtValidator(withAudience);
        return nimbusJwtDecoder;
    }

    private static RSAPublicKey stringToRsaKey(String publicKey) throws NoSuchAlgorithmException,
                                                                 InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        var pubKey = Base64.getDecoder().decode(publicKey);

        var x509 = new X509EncodedKeySpec(pubKey);
        return (RSAPublicKey) keyFactory.generatePublic(x509);
    }

    private static OAuth2TokenValidator<Jwt> audienceValidator() {
        return new JwtClaimValidator<List<String>>("aud", aud -> aud.contains("saral"));
    }
}


