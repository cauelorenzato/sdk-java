package com.veridu.idos.samples.management;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.ManagementFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class CredentialsSamples {
    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;

        /**
         * Token generated using the issuer credential public key and subject
         * credential public key key assigned with a issuer private key
         */
        String token = Utils.generateToken(Config.issuerPublicKey, Config.issuerPrivateKey, Config.issuerPublicKey);

        /**
         * ManagementFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an ProfileFactory Class, and accessed through this object
         * 
         * @param token
         *            The jwt token generated that authorizes requests to the
         *            API
         */
        ManagementFactory managementFactory = new ManagementFactory(token);

        /**
         * Gets the response from the API listing all credentials
         */
        JsonObject json = managementFactory.getCredential().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new credential
         */
        json = managementFactory.credential.create("Very Secure", "false");

        /**
         * Gets the public key of the created credential to retrieve the
         * credential giving the public key
         */
        String publicKey = json.get("data").getAsJsonObject().get("public").getAsString();

        /**
         * Get the response form the API geting one company
         */
        json = managementFactory.credential.getOne(publicKey);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the credential giving the public Key and a new name for it
         */
        json = managementFactory.credential.update("New Name", publicKey);

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Gets the new public key
         */
        publicKey = json.get("data").getAsJsonObject().get("public").getAsString();

        /**
         * Deletes the credential created giving the public key
         */
        json = managementFactory.credential.delete(publicKey);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

    }
}
