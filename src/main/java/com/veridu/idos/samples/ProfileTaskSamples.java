package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileTaskSamples {
    public static void main(String[] args) throws SDKException {
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
        String username = "9fd9f63e0d6487537569075da85a0c7f2";

        /**
         * Gets the response from the API listing all tasks
         */
        JsonObject json = credentialFactory.getTask().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Get the response form the API getting one task
         */
        json = credentialFactory.getTask().getOne(username, 1);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

    }

}
