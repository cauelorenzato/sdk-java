package com.veridu.idos.samples;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;

public class ServiceHandlerSamples {

    public static void main(String[] args) throws InvalidToken, SDKException {
        /**
         * JsonObject used to parse the response
         *
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;

        /**
         * IdOSAPIFactory is a class that instantiate all endpoints as their methods
         * (getEndpointName) are called. The endpoints don't need to be
         * instantiated one by one. You just need to call the
         * factory.getEndpoint and its going to be instantiated and available to
         * call its methods. In other words, it means that all endpoints is
         * going to pass by an CredentialFactory Class, and accessed through
         * this object
         *
         * @param companyPrivateKey
         *            The company public key that authorizes requests to the API
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Gets the response from the API listing all companies
         */
        JsonObject json = idOSAPIFactory.getServiceHandler().listAll();

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
         * Service id
         */
        int serviceId = 1860914067;

        /**
         * Creates the service
         */
        json = idOSAPIFactory.getServiceHandler().create(serviceId, listens);

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Create an array list containing who this service is going to listen
         * to
         */
        listens = new ArrayList<>();
        listens.add("idos:source.facebook.added");

        int serviceHandlerId = json.get("data").getAsJsonObject().get("id").getAsInt();

        /**
         * Updates the service giving the service handler id created
         */

        json = idOSAPIFactory.getServiceHandler().update(serviceHandlerId, listens);

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes the service giving the service id updated
         */
        json = idOSAPIFactory.getServiceHandler().delete(serviceHandlerId);

        /**
         * Prints the json response
         */
        System.out.println(json);

        /**
         * Deletes all service handlers
         */
        json = idOSAPIFactory.getServiceHandler().deleteAll();

        System.out.println(json.get("deleted").getAsInt());
    }
}
