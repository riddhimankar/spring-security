package com.saral.accountService.configs;

import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class JwtDecoderFactory {

    public static NimbusJwtDecoder getDecoder(String jwtDecoder,
                                              String secretKey,
                                              String publicKey,
                                              String jksUri){
        switch (jwtDecoder) {
            case "secret":
                String key = secretKey;
                SecretKey secret = new SecretKeySpec(key.getBytes(),
                                                     0,
                                                     key.getBytes().length,
                                                     "AES");
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

    private static RSAPublicKey stringToRsaKey(String publicKey) throws NoSuchAlgorithmException,
                                                                 InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        var pubKey = Base64.getDecoder().decode(publicKey);

        var x509 = new X509EncodedKeySpec(pubKey);
        return (RSAPublicKey) keyFactory.generatePublic(x509);
    }
}
