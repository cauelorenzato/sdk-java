package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

public class ProfileFeatures extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public ProfileFeatures(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
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
     * Retrieve a complete list of all features that belong to the given user, with filtering.
     * 
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/features", null, filter);
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
    public JsonObject create(String username, String name, int value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "Integer");
        return this.fetch("POST", "profiles/" + username + "/features", data);
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
    public JsonObject create(String username, String name, double value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "Double");
        return this.fetch("POST", "profiles/" + username + "/features", data);
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
    public JsonObject create(String username, String name, boolean value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "Boolean");
        return this.fetch("POST", "profiles/" + username + "/features", data);
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
        data.addProperty("type", "String");
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
        data.addProperty("type", "String");

        return this.fetch("PUT", "profiles/" + username + "/features/" + featureSlug, data);
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
    public JsonObject update(String username, String featureSlug, String name, double value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "Double");

        return this.fetch("PUT", "profiles/" + username + "/features/" + featureSlug, data);
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
    public JsonObject update(String username, String featureSlug, String name, int value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "Integer");

        return this.fetch("PUT", "profiles/" + username + "/features/" + featureSlug, data);
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
    public JsonObject update(String username, String featureSlug, String name, boolean value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);
        data.addProperty("type", "Boolean");

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

    /**
     * Deletes all features that belongs to the given user, with filtering
     * 
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/features", null, filter);
    }

}
