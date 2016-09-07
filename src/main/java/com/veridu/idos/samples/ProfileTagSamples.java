package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileTagSamples {

    public static void main(String[] args) throws SDKException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * IdOSAPIFactory is a class that instantiate all endpoints as their methods
         * (getEndpoi ntName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an IdOSAPIFactory Class, and accessed through this object
         * 
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all tags
         */
        JsonObject json = idOSAPIFactory.getTag().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new tag
         */
        json = idOSAPIFactory.getTag().create(username, "TagName", "tag-name");

        /**
         * Get the response form the API getting one tag
         */
        json = idOSAPIFactory.getTag().getOne(username, "tag-name");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the tag created giving the tag name
         */
        json = idOSAPIFactory.getTag().delete(username, "tag-name");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all profile tags related to the username
         */
        json = idOSAPIFactory.getTag().deleteAll(username);

        /**
         * Prints the number of deleted tags
         */
        System.out.println(json.get("deleted").getAsInt());
    }

}
