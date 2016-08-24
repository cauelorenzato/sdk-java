package com.veridu.sdk_java.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Utils {

    /**
     * Generate de credential token necessary for many requests to the API
     * 
     * @param issPublicKey
     *            The issuer credential public key
     * @param subjPublicKey
     *            The subject credential public key
     * @param issPrivKey
     *            The issuer credential private key
     * @return the generated token
     */
    public static String generateToken(String issPublicKey, String issPrivKey, String subjPublicKey) {
        byte[] apiKeySecretBytes = issPrivKey.getBytes();
        JwtBuilder jwt = Jwts.builder().setIssuer(issPublicKey).setSubject(subjPublicKey);
        String token = jwt.signWith(SignatureAlgorithm.HS256, apiKeySecretBytes).compact();
        return token;
    }

}
