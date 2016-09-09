package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

/**
 * Profile Attribute Reviews Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileAttributeReviews extends AbstractEndpoint {
    /**
     * Class constructor
     */
    public ProfileAttributeReviews(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Lists all reviews given an username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/reviews");
    }

    /**
     * Lists all reviews given an username, filtering by warning id
     * 
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/reviews", null, filter);
    }

    /**
     * Retrieves a review
     * 
     * @param username
     * @param reviewId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, Integer reviewId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/reviews/" + reviewId);
    }

    /**
     * Creates a review
     * 
     * @param username
     * @param warningId
     * @param positive
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, Integer warningId, Boolean positive)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("warningId", warningId);
        data.addProperty("positive", positive);

        return this.fetch("POST", "profiles/" + username + "/reviews", data);
    }

    /**
     * Updates a review
     * 
     * @param username
     * @param reviewId
     * @param positive
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, Integer reviewId, Boolean positive)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("positive", positive);

        return this.fetch("PUT", "profiles/" + username + "/reviews/" + reviewId, data);
    }

    /**
     * Deletes a review
     * 
     * @param username
     * @param reviewId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, Integer reviewId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/reviews/" + reviewId);
    }

    /**
     * Deletes all reviews
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/reviews");
    }

    /**
     * Deletes all reviews, filtering by warning id
     * 
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/reviews", null, filter);
    }
}
