package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.Factory;
import com.veridu.idos.exceptions.SDKException;

public class CompanySamples {

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
         *            The company public key that authorizes requests to the API
         */
        Factory factory = new Factory(IdOSHelper.getCredentials());

        /**
         * Gets the response from the API listing all companies
         */
        JsonObject json = factory.getCompany().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new company
         */
        json = factory.getCompany().create("A Company");

        /**
         * Gets the status of the response If true, gets the company that was
         * just created giving its slug If false, prints the message
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Get the response form the API geting one company
             */
            json = factory.getCompany().getOne("a-company");

            /**
             * Prints the array response
             */
            System.out.println(json.get("data").getAsJsonObject());
        } else {
            System.out.println(json.get("error").getAsString());
        }

        /**
         * Deletes the company that was just created giving its slug
         * 
         */
        json = factory.getCompany().delete("a-company");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

    }
}