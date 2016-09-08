package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public class HookSamples {
    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {

        /**
         * JsonObject used to parse the response
         * 
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;
        /**
         * IdOSAPIFactory is a class that instantiate all endpoints as their
         * methods (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         * 
         * @param companyPrivateKey
         *            The companyToken that authorizes requests to the API
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Gets the response from the API listing all Hooks (passing the
         * credential Public Key)
         */
        JsonObject json = idOSAPIFactory.getHook().listAll(Config.credentialPublicKey);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new hook
         */
        json = idOSAPIFactory.getHook().create(Config.credentialPublicKey, "trigger.test",
                "http://test.com/example.php", false);
        /**
         * Gets the id of the created hook to retrieve the hook by the id
         */
        int id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Get the response form the API geting one hook
         */
        json = idOSAPIFactory.getHook().getOne(Config.credentialPublicKey, id);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the hook giving the id
         */
        json = idOSAPIFactory.getHook().update(Config.credentialPublicKey, id, "trigger", "https://google.com", true);

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
        json = idOSAPIFactory.getHook().delete(Config.credentialPublicKey, id);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all hooks for the given credential public key
         */
        json = idOSAPIFactory.getHook().deleteAll(Config.credentialPublicKey);

        /**
         * Prints the number of deleted files
         */
        System.out.println(json.get("deleted").getAsInt());
    }
}
