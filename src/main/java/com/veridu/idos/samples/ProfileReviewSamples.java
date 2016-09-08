package com.veridu.idos.samples;

import com.google.gson.JsonObject;
import com.veridu.idos.IdOSAPIFactory;
import com.veridu.idos.exceptions.SDKException;

public class ProfileReviewSamples {

    public static void main(String[] args) throws SDKException {
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
         * going to pass by an IdOSAPIFactory Class, and accessed through this
         * object
         * 
         */
        IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(IdOSSamplesHelper.getCredentials());

        /* Username necessary for all requests of this endpoint */
        String username = "fd1fde2f31535a266ea7f70fdf224079";

        /**
         * Gets the response from the API listing all reviews
         */
        JsonObject json = idOSAPIFactory.getReview().listAll(username);

        /**
         * Prints the json
         */
        System.out.println(json);

        /**
         * Gets the response from the API trying to create a new review
         */
        json = idOSAPIFactory.getReview().create(username,
                json.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt(), true);

        /**
         * Get the response form the API getting one review
         */
        json = idOSAPIFactory.getReview().getOne(username, json.get("data").getAsJsonObject().get("id").getAsInt());

        /**
         * Prints the array response
         */
        System.out.println(json.get("data").getAsJsonObject());

        /**
         * Updates the review giving the review-slug
         */
        json = idOSAPIFactory.getReview().update(username, json.get("data").getAsJsonObject().get("id").getAsInt(),
                false);

        /**
         * Prints the json response
         */
        System.out.println(json);

    }

}
