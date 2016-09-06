package com.veridu.idos.samples.users;

import com.google.gson.JsonObject;
import com.veridu.idos.UserFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class SourceSamples {

    public static void main(String[] args) throws SDKException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * UserFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         * 
         */

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        UserFactory userFactory = new UserFactory(Config.credentialPrivateKey, Config.credentialPublicKey, username);

        /**
         * Gets the response from the API listing all flags
         */
        JsonObject json = userFactory.getSource().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);
        //
        // /**
        // * Gets the source id
        // */
        // HashMap<String, String> tags = new HashMap<>();
        // tags.put("otp_check", "email");
        // /**
        // * Gets the response from the API trying to create a new flag
        // */
        // json = userFactory.getSource().create(username, "email", tags);

        int sourceId = 1860914067;
        /**
         * Get the response form the API getting one flag
         */
        json = userFactory.getSource().getOne(username, sourceId);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the flag created giving the flag name
         */
        json = userFactory.getSource().delete(username, sourceId);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all profile flags related to the username
         */
        json = userFactory.getSource().deleteAll(username);

        /**
         * Prints the number of deleted flags
         */
        System.out.println(json.get("deleted").getAsInt());

        /**
         * Deletes all profile flags related to the username
         */
        json = userFactory.getSource().deleteAll(username);

        /**
         * Prints the number of deleted flags
         */
        System.out.println(json.get("deleted").getAsInt());
    }

}
