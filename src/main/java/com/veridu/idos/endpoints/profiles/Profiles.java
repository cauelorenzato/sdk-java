package com.veridu.idos.endpoints.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Profiles Endpoint Class
 * 
 * @version 2.0
 *
 */
public class Profiles extends AbstractEndpoint {

    /**
     * Class Constructor
     */
    public Profiles() {

    }

    /**
     * Lists all profiles
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "profiles");
    }

    /**
     * Retrieves a profile given its username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username);
    }

    /**
     * Updates a profile given its username
     * 
     * @param username
     * @param newUsername
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, String newUsername) throws SDKException, UnsupportedEncodingException {
        return this.fetch("PUT", "profiles/" + username, this.queryBuilder("name", newUsername));
    }

    /**
     * Deletes a profile given its username
     * 
     * @param username
     * @return
     * @throws SDKException
     */
    public JsonObject delete(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username);
    }

    /**
     * Deletes all profiles related to the target user
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll() throws SDKException {
        return this.fetch("DELETE", "profiles");
    }

}
