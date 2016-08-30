package com.veridu.idos.samples.management;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.ManagementFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class MemberSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {

        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;

        String token = Utils.generateToken(Config.issuerPublicKey, Config.issuerPrivateKey, Config.issuerPublicKey);

        /**
         * ManagementFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an Factory Class, and accessed through this object
         * 
         * @param privateKey
         *            The token that authorizes requests to the API
         */
        ManagementFactory managementFactory = new ManagementFactory(token);

        /**
         * Gets the response from the API listing all members
         */
        JsonObject json = managementFactory.getMember().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new member
         */
        json = managementFactory.member.create(Config.issuerPublicKey, "User", "member");
        /**
         * Gets the id of the created member to retrieve the member by the id
         */
        int id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Get the response form the API geting one member
         */
        json = managementFactory.member.getOne(id);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the member giving the id and changing the role
         */
        json = managementFactory.member.update(id, "admin");

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
        json = managementFactory.member.delete(id);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all members for the credential
         */
        json = managementFactory.member.deleteAll();

        /**
         * Prints the number of deleted files
         */
        System.out.println(json.get("data").getAsJsonObject().get("deleted").getAsInt());
    }
}
