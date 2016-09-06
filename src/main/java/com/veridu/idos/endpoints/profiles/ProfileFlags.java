package com.veridu.idos.endpoints.profiles;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Profile Flags Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileFlags extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public ProfileFlags(String token) {
        super(token);
    }

    /**
     * Lists all flags given an username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/flags");
    }

    /**
     * Retrieves a flag given its flag name
     * 
     * @param username
     * @param flagName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String flagName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/flags/" + flagName);
    }

    /**
     * Creates a flag passing the flag name
     * 
     * @param username
     * @param flagName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String flagName) throws SDKException {
        return this.fetch("POST", "profiles/" + username + "/flags/" + flagName);
    }

    /**
     * Deletes a flag given the flag name
     * 
     * @param username
     * @param flagName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String flagName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/flags/" + flagName);
    }

    /**
     * Deletes all flags associated with a username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/flags");
    }
}
