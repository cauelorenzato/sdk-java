package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.Factory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;

public class DigestedSamples {

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
        Factory factory = new Factory(Helper.getCredentials());

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /* source necessary for all requests of this endpoint */
        int source = 1860914067;

        /**
         * Gets the response from the API listing all digested data
         */
        JsonObject json = factory.getDigested().listAll(username, source);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new digested data
         */
        json = factory.getDigested().create(username, source, "Name", "Value");

        /**
         * Get the response form the API getting one digested data
         */
        json = factory.getDigested().getOne(username, source, "Name");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the digested data giving the digested name
         */
        json = factory.getDigested().update(username, source, "Name", "newValue");

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the credential created giving the digested name
         */
        json = factory.getDigested().delete(username, source, "Name");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all digested data
         */
        json = factory.getDigested().deleteAll(username, source);

        /**
         * Prints the number of deleted features
         */
        System.out.println(json.get("deleted").getAsInt());

    }
}
