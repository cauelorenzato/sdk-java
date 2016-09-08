package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * Profile Reviews Endpoint Class
 * 
 * @version 2.0
 */
public class ProfileReviews extends AbstractEndpoint {

    /**
     * Constructor Class
     * 
     * @param credentials
     * @throws InvalidToken
     */
    public ProfileReviews(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.MANAGEMENT);
    }

    /**
     * Lists all reviews for the given username
     * 
     * @param username
     * @return
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/reviews");
    }

    /**
     * Retrieves a review given its id
     * 
     * @param username
     * @param reviewId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int reviewId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/reviews/" + reviewId);
    }

    /**
     * Creates a new review
     * 
     * @param username
     * @param warningId
     * @param positive
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, int warningId, boolean positive) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("warning_id", warningId);
        data.addProperty("positive", positive);
        return this.fetch("POST", "profiles/" + username + "/reviews");
    }

    /**
     * Updates a review given its id
     * 
     * @param username
     * @param reviewId
     * @param positive
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject update(String username, int reviewId, boolean positive) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("positive", positive);

        return this.fetch("PUT", "profiles/" + username + "/reviews/" + reviewId, data);
    }
}
