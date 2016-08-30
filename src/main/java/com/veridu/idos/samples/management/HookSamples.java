package com.veridu.idos.samples.management;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.ManagementFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class HookSamples {
    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {

        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;

        String token = Utils.generateToken(Config.issuerPublicKey, Config.issuerPrivateKey, Config.issuerPublicKey);

        String credentialPublicKey = "credential-pub-key";

        /**
         * ManagementFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an ProfileFactory Class, and accessed through this object
         * 
         * @param privateKey
         *            The token that authorizes requests to the API
         */
        ManagementFactory managementFactory = new ManagementFactory(token);

        /**
         * Gets the response from the API listing all Hooks (passing the
         * credential Public Key)
         */
        JsonObject json = managementFactory.getHook().listAll(credentialPublicKey);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new hook
         */
        json = managementFactory.hook.create(credentialPublicKey, "trigger", "https://google.com", false);
        /**
         * Gets the id of the created hook to retrieve the hook by the id
         */
        int id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Get the response form the API geting one hook
         */
        json = managementFactory.hook.getOne(credentialPublicKey, id);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the hook giving the id
         */
        json = managementFactory.hook.update(credentialPublicKey, id, "trigger", "https://google.com", true);

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Gets the new id
         */
        id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Deletes the hook created giving the id
         */
        json = managementFactory.hook.delete(credentialPublicKey, id);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all hooks for the given credential public key
         */
        json = managementFactory.hook.deleteAll(credentialPublicKey);

        /**
         * Prints the number of deleted files
         */
        System.out.println(json.get("data").getAsJsonObject().get("deleted").getAsInt());
    }
}
