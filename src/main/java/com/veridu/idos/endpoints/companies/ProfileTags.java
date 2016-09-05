package com.veridu.idos.endpoints.companies;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Profile Tags Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileTags extends AbstractEndpoint {

    /**
     * Constructor class
     */
    public ProfileTags() {

    }

    /**
     * Lists all tags related to the username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/tags");
    }

    /**
     * Retrieves a tag given the tag name
     * 
     * @param username
     * @param tagName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String tagName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/tags/" + tagName);
    }

    /**
     * Creates a new tag
     * 
     * @param username
     * @param tagName
     * @param slug
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String tagName, String slug) throws SDKException {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", tagName);
        data.put("slug", slug);
        return this.fetch("POST", "profiles/" + username + "/tags");
    }

    /**
     * Deletes a tag given its tag name
     * 
     * @param username
     * @param tagName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String tagName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/tags/" + tagName);
    }

    /**
     * Deletes all tags associated with the username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/tags");
    }
}
