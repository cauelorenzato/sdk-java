package com.veridu.idos.samples.companies;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.CompanyFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class CompanySamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;

        /**
         * CompanyFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an Factory Class, and accessed through this object
         * 
         * @param privateKey
         *            The company public key that authorizes requests to the API
         */
        CompanyFactory companyFactory = new CompanyFactory(Config.privateKey);

        /**
         * Gets the response from the API listing all companies
         */
        JsonObject json = companyFactory.getCompany().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new company
         */
        json = companyFactory.company.create("Sample Company");

        /**
         * Gets the status of the response If true, gets the company that was
         * just created giving its slug If false, prints the message
         */
        if (json.get("status").getAsBoolean()) {
            /**
             * Get the response form the API geting one company
             */
            json = companyFactory.getCompany().getOne("sample-company");

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
        json = companyFactory.getCompany().delete("sample-company");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

    }
}