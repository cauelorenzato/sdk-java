package com.veridu.idos.samples.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.ProfileFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class NormalizedSamples {

    public static void main(String[] args) throws InvalidToken, SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * ProfileFactory is a class that instantiate all endpoints as their methods
         * (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an ProfileFactory Class, and accessed through this object
         * 
         */
        String token = Utils.generateToken(Config.issuerPublicKey, Config.issuerPrivateKey, Config.issuerPublicKey);

        ProfileFactory profileFactory = new ProfileFactory(token);

        String username = "9fd9f63e0d6487537569075da85a0c7f2";
        int source = 3;

        /**
         * Gets the response from the API listing all normalized data
         */
        JsonObject json = profileFactory.getNormalized().listAll(username, source);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new normalized data
         */
        json = profileFactory.normalized.create(username, source, "Name", "value");

        /**
         * Get the response form the API getting one normalized data
         */
        json = profileFactory.normalized.getOne(username, source, "source-3-normalized-1");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the normalized data giving the normalizedName
         */
        json = profileFactory.normalized.update(username, source, "normalized-name", "new name", "new value");

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the credential created giving the normalized name
         */
        json = profileFactory.normalized.delete(username, source, "normalized-name");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all normalized data
         */
        json = profileFactory.normalized.deleteAll(username, source);

        /**
         * Prints the number of deleted features
         */
        System.out.println(json.get("deleted").getAsInt());

    }
}
