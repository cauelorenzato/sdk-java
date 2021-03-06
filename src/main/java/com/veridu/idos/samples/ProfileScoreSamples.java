package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileScoreSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * IdOSAPIFactory is a class that instantiate all endpoints as their methods
         * (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an IdOSAPIFactory Class, and accessed through this object
         * 
         */
        IdOSAPIFactory credentialFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Creates a new attribute to make requests for the attribute' scores
         */
        // JsonObject json = credentialFactory.getAttribute().create(username, "attributeName", "attributeValue");
        /**
         * Gets the response from the API listing all scores
         */
        // json = credentialFactory.getScore().listAll(username, "attributeName");

        /**
         * Prints the json
         */
        // System.out.println(json);

        /**
         * Gets the response from the API trying to create a new score
         */
        // json = credentialFactory.getScore().create(username, "attributeName", "nameTest", 0.6);

        /**
         * Get the response form the API getting one score
         */
        // json = credentialFactory.getScore().getOne(username, "attributeName", "nameTest");

        /**
         * Prints the array response
         */
        // System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the score created giving the score name
         */
        // json = credentialFactory.getScore().delete(username, "attributeName", "nameTest");

        /**
         * Prints the status of the request
        /       */
        // System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all attribute scores related to the username
         */
        // json = credentialFactory.getScore().deleteAll(username, "attributeName");

        /**
         * Prints the number of deleted scores
         */
        // System.out.println(json.get("deleted").getAsInt());
    }

}
