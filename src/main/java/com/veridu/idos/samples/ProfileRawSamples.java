package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileRawSamples {

    public static void main(String[] args) throws UnsupportedEncodingException, SDKException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * IdOSAPIFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an IdOSAPIFactory Class, and accessed through this
         * object
         * 
         */
        IdOSAPIFactory credentialFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        JsonObject json = credentialFactory.getSource().listAll(username);
        int sourceId = json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt();
        /**
         * Gets the response from the API listing all raw
         */
        // json = credentialFactory.getRaw().listAll(username, sourceId);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new raw
         */
        json = credentialFactory.getRaw().create(username, sourceId, "collection-test", "value-test");

        /**
         * Get the response form the API getting one raw
         */
        // json = credentialFactory.getRaw().getOne(username, sourceId, "collection-test");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the raw created giving the raw name
         */
        // json = credentialFactory.getRaw().delete(username, sourceId, "collection-test");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all attribute raw related to the username
         */
        // json = credentialFactory.getRaw().deleteAll(username, sourceId);

        /**
         * Prints the number of deleted raw
         */
        System.out.println(json.get("deleted").getAsInt());
    }

}
