package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.Factory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class FeatureSamples {
    public static void main(String[] args) throws InvalidToken, SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * Factory is a class that instantiate all endpoints as their methods
         * (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an Factory Class, and accessed through this object
         * 
         */
        String token = Utils.generateToken(Config.issuerPublicKey, Config.issuerPrivateKey, Config.issuerPublicKey);

        Factory factory = new Factory(token);

        /* Username necessary for all requests of this endpoint */
        String username = "9fd9f63e0d6487537569075da85a0c7f2";

        /**
         * Gets the response from the API listing all features
         */
        JsonObject json = factory.getFeature().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new feature
         */
        json = factory.feature.create(username, "Name", "value");

        /**
         * Get the response form the API getting one feature
         */
        json = factory.feature.getOne(username, "feature-slug");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the feature giving the feature-slug
         */
        json = factory.feature.update(username, "feature-slug", "new name", "new value");

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the credential created giving the feature name
         */
        json = factory.feature.delete(username, "feature-slug");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all features
         */
        json = factory.feature.deleteAll(username);

        /**
         * Prints the number of deleted features
         */
        System.out.println(json.get("deleted").getAsInt());
    }
}
