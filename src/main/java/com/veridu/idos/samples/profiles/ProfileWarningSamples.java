package com.veridu.idos.samples.profiles;

import com.google.gson.JsonObject;
import com.veridu.idos.CredentialFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.IdOSUtils;

public class ProfileWarningSamples {

    public static void main(String[] args) throws SDKException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * CredentialFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         * 
         */
        String token = IdOSUtils.generateToken(Config.issuerPrivateKey, Config.issuerPublicKey,
                Config.credentialPublicKey);

        CredentialFactory credentialFactory = new CredentialFactory(token);

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all warnings
         */
        JsonObject json = credentialFactory.getWarning().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new warning
         */
        json = credentialFactory.getWarning().create(username, "Testing", "firstName");

        /**
         * Get the response form the API getting one warning
         */
        json = credentialFactory.getWarning().getOne(username, "testing");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the warning giving the warning slug
         */
        json = credentialFactory.getWarning().delete(username, "testing");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all warnings
         */
        json = credentialFactory.getWarning().deleteAll(username);

        /**
         * Prints the number of deleted warnings
         */
        System.out.println(json.get("deleted").getAsInt());

    }

}
