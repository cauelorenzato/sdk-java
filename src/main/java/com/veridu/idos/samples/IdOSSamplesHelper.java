package com.veridu.idos.samples;

import java.util.HashMap;

import com.veridu.idos.settings.Config;

public class IdOSSamplesHelper {
    /**
     * Credentials for making requests to the api
     */
    public static HashMap<String, String> credentials = new HashMap<>();

    public static HashMap getCredentials() {
        credentials.put("companyPrivateKey", Config.privateKey);
        credentials.put("companyPublicKey", Config.publicKey);
        credentials.put("credentialPrivateKey", Config.credentialPrivateKey);
        credentials.put("credentialPublicKey", Config.credentialPublicKey);
        credentials.put("handlerPrivateKey", Config.issuerPrivateKey);
        credentials.put("handlerPublicKey", Config.issuerPublicKey);
        credentials.put("username", "fd1fde2f31535a266ea7f70fdf224079");

        return credentials;
    }
}
