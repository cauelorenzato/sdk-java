package com.veridu.idos.samples.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.CredentialFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class DigestedSamples {

    public static void main(String[] args) throws InvalidToken, SDKException, UnsupportedEncodingException {
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

        /* source necessary for all requests of this endpoint */
        int source = 1860914067;

        /**
         * Gets the response from the API listing all digested data
         */
        JsonObject json = credentialFactory.getDigested().listAll(username, source);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new digested data
         */
        json = credentialFactory.digested.create(username, source, "Name", "Value");

        /**
         * Get the response form the API getting one digested data
         */
        json = credentialFactory.digested.getOne(username, source, "Name");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the digested data giving the digested name
         */
        json = credentialFactory.digested.update(username, source, "Name", "newValue");

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the credential created giving the digested name
         */
        json = credentialFactory.digested.delete(username, source, "Name");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all digested data
         */
        json = credentialFactory.digested.deleteAll(username, source);

        /**
         * Prints the number of deleted features
         */
        System.out.println(json.get("deleted").getAsInt());

    }
}
