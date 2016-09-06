package com.veridu.idos.samples.companies;

import com.google.gson.JsonObject;
import com.veridu.idos.CompanyFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class ProfileTagSamples {

    public static void main(String[] args) throws SDKException {
        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * CompanyFactory is a class that instantiate all endpoints as their
         * methods (getEndpoi ntName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an CompanyFactory Class, and accessed through this
         * object
         * 
         */
        CompanyFactory companyFactory = new CompanyFactory(Config.privateKey, Config.publicKey);

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all tags
         */
        JsonObject json = companyFactory.getTag().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new tag
         */
        json = companyFactory.getTag().create(username, "Tag Name", "tag-name");

        /**
         * Get the response form the API getting one tag
         */
        json = companyFactory.getTag().getOne(username, "tag-name");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Deletes the tag created giving the tag name
         */
        json = companyFactory.getTag().delete(username, "tag-name");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all profile tags related to the username
         */
        json = companyFactory.getTag().deleteAll(username);

        /**
         * Prints the number of deleted tags
         */
        System.out.println(json.get("deleted").getAsInt());
    }

}
