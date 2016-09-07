package com.veridu.idos.samples;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.Factory;
import com.veridu.idos.exceptions.SDKException;

public class ServiceSamples {

    public static void main(String[] args) throws SDKException {
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
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         *
         * @param privateKey
         *            The company public key that authorizes requests to the API
         */
        Factory factory = new Factory(Helper.getCredentials());

        /**
         * Gets the response from the API listing all companies
         */
        JsonObject json = factory.getService().listAll();

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Create an array list containing who this service is going to listen
         * to
         */
        ArrayList<String> listens = new ArrayList<>();
        listens.add("source.add.facebook");

        /**
         * Create an array list containing who this service is going to trigger
         */
        ArrayList<String> triggers = new ArrayList<>();
        triggers.add("source.scraper.facebook.finished");

        /**
         * Creates the service
         */
        json = factory.getService().create("New service name", "http://service-url.com", true, 1, "idos", "secret",
                listens, triggers);

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Creates a Hash of data giving the parameters to update
         */
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", "Other Service Name");

        System.out.println(json);
        /**
         * Updates the service giving the service id created
         */
        json = factory.getService().update(json.get("data").getAsJsonObject().get("id").getAsInt(), data);

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the service giving the service id updated
         */
        json = factory.getService().delete(json.get("data").getAsJsonObject().get("id").getAsInt());

        /**
         * Prints the json response
         */
        System.out.println(json);

        json = factory.getService().deleteAll();

        System.out.println(json.get("deleted").getAsInt());
    }
}
