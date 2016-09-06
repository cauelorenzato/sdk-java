package com.veridu.idos.samples.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.CredentialFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileReferenceSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * CredentialFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         * 
         */
        CredentialFactory credentialFactory = new CredentialFactory(Config.issuerPrivateKey, Config.issuerPublicKey,
                Config.credentialPublicKey);

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all references
         */
        JsonObject json = credentialFactory.getReference().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new reference
         */
        json = credentialFactory.getReference().create(username, "attributeName", "attributeValue");

        /**
         * Get the response form the API getting one reference
         */
        json = credentialFactory.getReference().getOne(username, "attributeName");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the reference created giving the reference name
         */
        json = credentialFactory.getReference().delete(username, "attributeName");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all profile references related to the username
         */
        json = credentialFactory.getReference().deleteAll(username);

        /**
         * Prints the number of deleted references
         */
        System.out.println(json.get("deleted").getAsInt());

    }

}
