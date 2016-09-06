package com.veridu.idos.endpoints.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

public class ProfileFeatures extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public ProfileFeatures(String token) {
        super(token);
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
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);

        return this.fetch("POST", "profiles/" + username + "/features", data);
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
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);

        return this.fetch("PUT", "profiles/" + username + "/features/" + featureSlug, data);
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
