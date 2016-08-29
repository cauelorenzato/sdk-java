package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;

public class Features extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public Features() {
    }

    /**
     * Retrieve a complete list of all features that belong to the given user.
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/features");
    }

    /**
     * Retrieves all public information from a Feature for the given user
     * 
     * @param username
     * @param featureSlug
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String featureSlug) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/features/" + featureSlug);
    }

    /**
     * Create a new feature for the given user.
     * 
     * @param username
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String name, String value)
            throws SDKException, UnsupportedEncodingException {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("value", value);

        return this.fetch("POST", "profiles/" + username + "/features", this.queryBuilder(data));
    }

    /**
     * Updates Feature's specific information.
     * 
     * @param username
     * @param featureSlug
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, String featureSlug, String name, String value)
            throws SDKException, UnsupportedEncodingException {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("value", value);

        return this.fetch("PUT", "profiles/" + username + "/features/" + featureSlug, this.queryBuilder(data));
    }

    /**
     * Deletes a single Feature that belongs to the given user
     * 
     * @param username
     * @param featureSlug
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String featureSlug) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/features/" + featureSlug);
    }

    /**
     * Deletes all features that belongs to the given user
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/features");
    }

}
