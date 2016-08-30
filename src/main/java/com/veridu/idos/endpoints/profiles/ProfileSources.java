package com.veridu.idos.endpoints.profiles;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Profile Sources Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileSources extends AbstractEndpoint {

    /**
     * Constructor class
     */
    public ProfileSources() {

    }

    /**
     * Lists all sources given an username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources");
    }

    @FIXME
    /**
     * (não vai existir esse endpoint
     *
     * getOne() ?)
     */
    // /**
    // * Retrieves a source given its source name
    // *
    // * @param username
    // * @param sourceName
    // * @return JsonObject response
    // * @throws SDKException
    // */
    // public JsonObject getOne(String username, String sourceName) throws
    // SDKException {
    // return this.fetch("GET", "profiles/" + username + "/sources/" +
    // sourceName);
    // }

    /**
     * Creates a source passing the source name
     * 
     * @param username
     * @param sourceName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String sourceName) throws SDKException {
        return this.fetch("POST", "profiles/" + username + "/sources/" + sourceName);
    }

    /**
     * Deletes a source given the source name
     * 
     * @param username
     * @param sourceName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String sourceName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceName);
    }

    /**
     * Deletes all sources associated with a username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources");
    }

}
