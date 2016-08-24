package com.veridu.sdk_java.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Utils {

    /**
     * Generate de credential token necessary for many requests to the API
     * 
     * @param issuerPublicKey
     *            The issuer credential public key
     * @param issuerPrivateKey
     *            The issuer credential private key
     * @param subjectPublicKey
     *            The subject credential public key
     * @return the generated token
     */
    public static String generateToken(String issuerPublicKey, String issuerPrivateKey, String subjectPublicKey) {
        byte[] apiKeySecretBytes = issuerPrivateKey.getBytes();
        JwtBuilder jwt = Jwts.builder().setIssuer(issuerPublicKey).setSubject(subjectPublicKey);
        String token = jwt.signWith(SignatureAlgorithm.HS256, apiKeySecretBytes).compact();
        return token;
    }

}
