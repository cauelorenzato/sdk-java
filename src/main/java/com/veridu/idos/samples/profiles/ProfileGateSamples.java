package com.veridu.idos.samples.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.CredentialFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class ProfileGateSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
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
        String token = Utils.generateToken(Config.issuerPrivateKey, Config.issuerPublicKey, Config.credentialPublicKey);

        CredentialFactory credentialFactory = new CredentialFactory(token);

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all gates
         */
        JsonObject json = credentialFactory.getGate().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new gate
         */
        json = credentialFactory.gate.create(username, "Gate Name", false);

        /**
         * Get the response form the API getting one gate
         */
        json = credentialFactory.gate.getOne(username, "gate-name");

        /**
         * Updates the gate password
         */
        json = credentialFactory.gate.update(username, "gate-name", true);
        /**
         * Prints the array response
         */
        System.out.println(json);

        /**
         * Deletes the gate created giving the gate name
         */
        json = credentialFactory.gate.delete(username, "gate-name");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all profile gates related to the username
         */
        json = credentialFactory.gate.deleteAll(username);

        /**
         * Prints the number of deleted gates
         */
        System.out.println(json.get("deleted").getAsInt());

    }
}