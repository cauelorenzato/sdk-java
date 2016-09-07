package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class PermissionSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;

        /**
         * IdOSAPIFactory is a class that instantiate all endpoints (related to
         * /companies) as their methods (getEndpointName) are called. The
         * endpoints don't need to be instantiated one by one. You just need to
         * call the factory.getEndpoint and its going to be instantiated and
         * available to call its methods. In other words, it means that all
         * endpoints is going to pass by an CredentialFactory Class, and
         * accessed through this object
         * 
         * @param privateKey
         *            The company public key that authorizes requests to the API
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Gets the response from the API listing all permissions
         */
        JsonObject json = idOSAPIFactory.getPermission().listAll("veridu-ltd");

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new permission
         */
        json = idOSAPIFactory.getPermission().create("veridu-ltd", "hi:world");

        /**
         * Gets the status of the response If true, gets the permission that was
         * just created giving its slug. If false, prints the message
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Get the response form the API geting one company
             */
            json = idOSAPIFactory.getPermission().getOne("veridu-ltd", "hi:world");

            /**
             * Prints the array response
             */
            System.out.println(json.get("data").getAsJsonObject());
        } else {
            System.out.println(json.get("error").getAsString());
        }

        /**
         * Deletes the permission that was just created giving its slug and
         * route name
         * 
         */
        json = idOSAPIFactory.getPermission().delete("veridu-ltd", "hi:world");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());
    }
}
