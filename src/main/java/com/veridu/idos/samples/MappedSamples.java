package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.Factory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class MappedSamples {

    public static void main(String[] args) throws InvalidToken, SDKException, UnsupportedEncodingException {
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
         * going to pass by an Factory Class, and accessed through this object
         * 
         */
        String token = Utils.generateToken(Config.issuerPublicKey, Config.issuerPrivateKey, Config.issuerPublicKey);

        Factory factory = new Factory(token);

        String username = "9fd9f63e0d6487537569075da85a0c7f2";
        int source = 3;

        /**
         * Gets the response from the API listing all mapped data
         */
        JsonObject json = factory.getMapped().listAll(username, source);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new mapped data
         */
        json = factory.mapped.create(username, source, "Name", "value");

        /**
         * Get the response form the API getting one mapped data
         */
        json = factory.mapped.getOne(username, source, "source-3-mapped-1");

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the mapped data giving the mappedName
         */
        json = factory.mapped.update(username, source, "mapped-name", "new name", "new value");

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the credential created giving the mapped name
         */
        json = factory.mapped.delete(username, source, "mapped-name");

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all mapped data
         */
        json = factory.mapped.deleteAll(username, source);

        /**
         * Prints the number of deleted features
         */
        System.out.println(json.get("deleted").getAsInt());

    }
}
