package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class RoleAccessSamples {

    public static void main(String[] args) throws SDKException {
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
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         *
         * @param rolePrivateKey
         *            The role public key that authorizes requests to the API
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Gets the response from the API listing all roles
         */
        JsonObject json = idOSAPIFactory.getRole().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new role
         */
        json = idOSAPIFactory.getRole().create("company.owner", 7, "new-resource");

        /**
         * Gets the status of the response If true, gets the role that was just
         * created giving its slug If false, prints the message
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Get the response form the API geting one role
             */
            json = idOSAPIFactory.getRole().getOne(json.get("id").getAsInt());

            /**
             * Prints the array response
             */
            System.out.println(json.get("data").getAsJsonObject());
        } else {
            System.out.println(json.get("error").getAsString());
        }

        /**
         * Deletes the role that was just created giving its slug
         *
         */
        json = idOSAPIFactory.getRole().delete(json.get("id").getAsInt());

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all role access
         */
        json = idOSAPIFactory.getRole().deleteAll();

        /**
         * Prints the number of deleted role access
         */
        System.out.println(json.get("deleted").getAsInt());
    }

}
