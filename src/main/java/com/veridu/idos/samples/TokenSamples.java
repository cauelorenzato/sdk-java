package com.veridu.idos.samples;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class TokenSamples {

    public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
        /**
         * JsonObject used to parse the response
         *
         * @see https://github.com/google/gson
         */
        JsonObject parsed = null;

        /**
         * IdOSAPIFactory is a class that instantiate all endpoints that requires the
         * UserToken Authorization. The endpoints don't need to be instantiated
         * one by one. You just need to call the factory.getEndpoint and its
         * going to be instantiated and available to call its methods. In other
         * words, it means that all endpoints is going to pass by an IdOSAPIFactory
         * Class, and accessed through this object
         * 
         * The userToken will be stored at factory.userToken;
         *
         * @param companyPrivateKey
         *            The company public key that authorizes requests to the API
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /**
         * Exchanges the user token to company token, passing the company slug
         */
        JsonObject json = idOSAPIFactory.getToken().exchangeToken("veridu-ltd");

        String companyToken = json.get("data").getAsString();
        /**
         * Prints the response
         */
        System.out.println(companyToken);
    }

}
