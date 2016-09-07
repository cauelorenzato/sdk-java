package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.Factory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class MemberSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {

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
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         * 
         * @param privateKey
         *            The companyToken that authorizes requests to the API
         */
        Factory factory = new Factory(IdOSSamplesHelper.getCredentials());

        /**
         * Gets the response from the API listing all members
         */
        JsonObject json = factory.getMember().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new member
         */
        json = factory.getMember().create(Config.credentialPublicKey, "fd1fde2f31535a266ea7f70fdf224079", "Employee");
        /**
         * Gets the id of the created member to retrieve the member by the id
         */
        int id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Get the response form the API geting one member
         */
        json = factory.getMember().getOne(id);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the member giving the id and changing the role
         */
        json = factory.getMember().update(id, "admin");

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Gets the new id
         */
        id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Deletes the member created giving the id
         */
        json = factory.getMember().delete(id);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all members for the credential
         */
        json = factory.getMember().deleteAll();

        /**
         * Prints the number of deleted files
         */
        System.out.println(json.get("deleted").getAsInt());
    }
}
