package com.veridu.idos.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Utils {

    /**
     * Generates companyToken necessary for many requests to the API
     * 
     * @param issuerPublicKey
     *            The issuer credential public key
     * @param issuerPrivateKey
     *            The issuer credential private key
     * @param subjectPublicKey
     *            The subject credential public key
     * @return the generated companyToken
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
     * Generates a company companyToken when subject exists
     * 
     * @param privateKey
     * @param publicKey
     * @param subject
     * @return String companyToken
     */
    public static String generateCompanyToken(String privateKey, String publicKey, String subject) {
        return Utils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a company companyToken without subject
     * 
     * @param privateKey
     * @param publicKey
     * @return String companyToken
     */
    public static String generateCompanyToken(String privateKey, String publicKey) {
        return Utils.generateToken(privateKey, publicKey, "");
    }

    /**
     * Generates a credential companyToken when subject exists
     * 
     * @param privateKey
     * @param publicKey
     * @param subject
     * @return String companyToken
     */
    public static String generateCredentialToken(String privateKey, String publicKey, String subject) {
        return Utils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a user companyToken when subject exists
     * 
     * @param privateKey
     * @param publicKey
     * @param subject
     * @return String companyToken
     */
    public static String generateUserToken(String privateKey, String publicKey, String subject) {
        return Utils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a user companyToken without subject
     * 
     * @param privateKey
     * @param publicKey
     * @return
     */
    public static String generateUserToken(String privateKey, String publicKey) {
        return Utils.generateToken(privateKey, publicKey, "");
    }
}
