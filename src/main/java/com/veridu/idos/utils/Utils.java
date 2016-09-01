package com.veridu.idos.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Utils {

    /**
     * Generates token necessary for many requests to the API
     * 
     * @param issuerPublicKey
     *            The issuer credential public key
     * @param issuerPrivateKey
     *            The issuer credential private key
     * @param subjectPublicKey
     *            The subject credential public key
     * @return the generated token
     */
    public static String generateToken(String issuerPrivateKey, String issuerPublicKey, String subjectPublicKey) {
        byte[] apiKeySecretBytes = issuerPrivateKey.getBytes();
        JwtBuilder jwt = Jwts.builder().setIssuer(issuerPublicKey);
        if (!subjectPublicKey.isEmpty()) {
            jwt.setSubject(subjectPublicKey);
        }
        String token = jwt.signWith(SignatureAlgorithm.HS256, apiKeySecretBytes).compact();
        System.out.println(token);
        return token;
    }

    /**
     * Generates a company token when subject exists
     * 
     * @param privateKey
     * @param publicKey
     * @param subject
     * @return String token
     */
    public static String generateCompanyToken(String privateKey, String publicKey, String subject) {
        return Utils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a company token without subject
     * 
     * @param privateKey
     * @param publicKey
     * @return String token
     */
    public static String generateCompanyToken(String privateKey, String publicKey) {
        return Utils.generateToken(privateKey, publicKey, "");
    }

    /**
     * Generates a credential token when subject exists
     * 
     * @param privateKey
     * @param publicKey
     * @param subject
     * @return String token
     */
    public static String generateCredentialToken(String privateKey, String publicKey, String subject) {
        return Utils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a user token when subject exists
     * 
     * @param privateKey
     * @param publicKey
     * @param subject
     * @return String token
     */
    public static String generateUserToken(String privateKey, String publicKey, String subject) {
        return Utils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a user token without subject
     * 
     * @param privateKey
     * @param publicKey
     * @return
     */
    public static String generateUserToken(String privateKey, String publicKey) {
        return Utils.generateToken(privateKey, publicKey, "");
    }
}
