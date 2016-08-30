package com.veridu.idos.endpoints.profiles;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Profile References Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileReferences extends AbstractEndpoint {

    /**
     * Constructor class
     */
    public ProfileReferences() {

    }

    /**
     * Lists all references given an username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/references");
    }

    /**
     * Retrieves a reference given its attribute name
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String attributeName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/references/" + attributeName);
    }

    /**
     * Creates a reference passing the attribute name
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String attributeName) throws SDKException {
        return this.fetch("POST", "profiles/" + username + "/references/" + attributeName);
    }

    /**
     * Deletes a reference given the attribute name
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String attributeName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/references/" + attributeName);
    }

    /**
     * Deletes all references associated with a username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/references");
    }
}
