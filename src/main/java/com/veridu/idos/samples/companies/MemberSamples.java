package com.veridu.idos.samples.companies;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.CompanyFactory;
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
         * CompanyFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         * 
         * @param privateKey
         *            The companyToken that authorizes requests to the API
         */
        CompanyFactory companyFactory = new CompanyFactory(Config.privateKey, Config.publicKey);

        /**
         * Gets the response from the API listing all members
         */
        JsonObject json = companyFactory.getMember().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new member
         */
        json = companyFactory.member.create(Config.credentialPublicKey, "fd1fde2f31535a266ea7f70fdf224079", "Employee");
        /**
         * Gets the id of the created member to retrieve the member by the id
         */
        int id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Get the response form the API geting one member
         */
        json = companyFactory.member.getOne(id);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the member giving the id and changing the role
         */
        json = companyFactory.member.update(id, "admin");

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
        json = companyFactory.member.delete(id);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all members for the credential
         */
        json = companyFactory.member.deleteAll();

        /**
         * Prints the number of deleted files
         */
        System.out.println(json.get("deleted").getAsInt());
    }
}
