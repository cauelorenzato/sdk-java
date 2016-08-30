package com.veridu.idos.samples.management;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.ManagementFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Utils;

public class SettingSamples {

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
         * @param token
         *            The credential token that authorizes requests to the API
         */
        ManagementFactory management = new ManagementFactory(token);

        /**
         * Gets the response from the API listing all settings
         */
        JsonObject json = management.getSetting().listAll();
        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new setting
         */
        json = management.setting.create("SectionEx", "PropertyEx", "ValueEx");

        /**
         * Gets the id of the created setting
         */
        int id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Get the response from the API getting one setting
         */
        json = management.setting.getOne(id);

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * get the updated id for the updated setting
         */
        id = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Deletes the setting that was just created giving its slug
         *
         */
        json = management.setting.delete(id);

        /**
         * Prints the status of the request
         */
        System.out.println(json.get("status").getAsBoolean());

        /**
         * Deletes all settings for the given token
         */
        json = management.setting.deleteAll();

        /**
         * prints the number of deleted settings
         */
        System.out.println(json.get("deleted").getAsInt());
    }

}
