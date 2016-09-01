package com.veridu.idos.samples.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.CredentialFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class NormalisedSamples {

    public static void main(String[] args) throws InvalidToken, SDKException, UnsupportedEncodingException {
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
        CredentialFactory credentialFactory = new CredentialFactory(Config.issuerPrivateKey, Config.issuerPublicKey,
                Config.credentialPublicKey);

        String username = "9fd9f63e0d6487537569075da85a0c7f2";
        int source = 1860914067;
        /**
         * Gets the response from the API listing all normalised data
         */
        JsonObject json = credentialFactory.getNormalized().listAll(username, source);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new normalised data
         */
        json = credentialFactory.normalised.create(username, source, "Name", "value");

        /**
         * Get the response form the API getting one normalised data
         */
        json = credentialFactory.normalised.getOne(username, source, "source-3-normalised-1");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the normalised data giving the normalizedName
         */
        json = credentialFactory.normalised.update(username, source, "normalised-name", "new name", "new value");

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the credential created giving the normalised name
         */
        json = credentialFactory.normalised.delete(username, source, "normalised-name");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all normalised data
         */
        json = credentialFactory.normalised.deleteAll(username, source);

        /**
         * Prints the number of deleted features
         */
        System.out.println(json.get("deleted").getAsInt());

    }
}
