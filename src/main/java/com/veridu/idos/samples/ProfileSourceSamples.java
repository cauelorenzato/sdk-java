package com.veridu.idos.samples;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.Factory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileSourceSamples {

    public static void main(String[] args) throws SDKException {
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
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         * 
         */

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        Factory factory = new Factory(IdOSSamplesHelper.getCredentials());

        /**
         * Gets the response from the API listing all sources
         */
        JsonObject json = factory.getSource().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the source id
         */
        HashMap<String, String> tags = new HashMap<>();
        tags.put("otp_check", "sms");
        /**
         * Gets the response from the API trying to create a new source
         */
        json = factory.getSource().create(username, "email", tags);

        int sourceId = 1860914067;
        /**
         * Get the response form the API getting one source
         */
        json = factory.getSource().getOne(username, sourceId);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the source created giving the source name
         */
        json = factory.getSource().delete(username, sourceId);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all profile sources related to the username
         */
        json = factory.getSource().deleteAll(username);

        /**
         * Prints the number of deleted sources
         */
        System.out.println(json.get("deleted").getAsInt());

        /**
         * Deletes all profile sources related to the username
         */
        json = factory.getSource().deleteAll(username);

        /**
         * Prints the number of deleted sources
         */
        System.out.println(json.get("deleted").getAsInt());
    }

}
