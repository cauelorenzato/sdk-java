package com.veridu.idos.samples.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.CredentialFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.IdOSUtils;

public class ProfileFeatureSamples {
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
         * going to pass by an CredentialFactory Class, and accessed through this
         * object
         * 
         */
        String token = IdOSUtils.generateToken(Config.issuerPrivateKey, Config.issuerPublicKey, Config.credentialPublicKey);

        CredentialFactory credentialFactory = new CredentialFactory(token);

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all features
         */
        JsonObject json = credentialFactory.getFeature().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new feature
         */
        json = credentialFactory.feature.create(username, "Testing", "testing");

        /**
         * Get the response form the API getting one feature
         */
        json = credentialFactory.feature.getOne(username, "testing");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the feature giving the feature-slug
         */
        json = credentialFactory.feature.update(username, "testing", "New testing", "new value");

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the credential feature giving the feature name
         */
        json = credentialFactory.feature.delete(username, "new-testing");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all features
         */
        json = credentialFactory.feature.deleteAll(username);

        /**
         * Prints the number of deleted features
         */
        System.out.println(json.get("deleted").getAsInt());
    }
}
