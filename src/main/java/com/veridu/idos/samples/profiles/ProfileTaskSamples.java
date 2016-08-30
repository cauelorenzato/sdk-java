package com.veridu.idos.samples.profiles;

import com.google.gson.JsonObject;
import com.veridu.idos.ProfileFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class ProfileTaskSamples {
    public static void main(String[] args) throws SDKException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * ProfileFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an ProfileFactory Class, and accessed through this
         * object
         * 
         */
        String token = Utils.generateToken(Config.issuerPublicKey, Config.issuerPrivateKey, Config.issuerPublicKey);

        ProfileFactory profileFactory = new ProfileFactory(token);

        /* Username necessary for all requests of this endpoint */
        String username = "9fd9f63e0d6487537569075da85a0c7f2";

        /**
         * Gets the response from the API listing all tasks
         */
        JsonObject json = profileFactory.getTask().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Get the response form the API getting one task
         */
        json = profileFactory.task.getOne(username, 1);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

    }

}
