package com.veridu.idos.samples.companies;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.CompanyFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class CredentialsSamples {
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
         * going to pass by an CredentialFactory Class, and accessed through this
         * object
         * 
         * @param companyToken
         *            The jwt companyToken generated that authorizes requests to the
         *            API
         */
        CompanyFactory companyFactory = new CompanyFactory(Config.privateKey, Config.publicKey);

        /**
         * Gets the response from the API listing all credentials
         */
        JsonObject json = companyFactory.getCredential().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new credential
         */
        json = companyFactory.credential.create("Very Secure", "false");

        /**
         * Gets the public key of the created credential to retrieve the
         * credential giving the public key
         */
        String publicKey = json.get("data").getAsJsonObject().get("public").getAsString();

        /**
         * Get the response form the API geting one company
         */
        json = companyFactory.credential.getOne(publicKey);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the credential giving the public Key and a new name for it
         */
        json = companyFactory.credential.update("New Name", publicKey);

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Gets the new public key
         */
        publicKey = json.get("data").getAsJsonObject().get("public").getAsString();

        /**
         * Deletes the credential created giving the public key
         */
        json = companyFactory.credential.delete(publicKey);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

    }
}
