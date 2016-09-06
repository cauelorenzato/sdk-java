package com.veridu.idos.endpoints.users;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Sources Endpoint class
 * 
 * @version 2.0
 *
 */
public class Sources extends AbstractEndpoint {

    /**
     * Constructor class
     * 
     * @param token
     */
    public Sources(String token) {
        super(token);
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

    /**
     * Retrieves a source given its source name
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int sourceId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId);
    }

    /**
     * Creates a source passing the source name
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String name, HashMap<String, String> tags) throws SDKException {
        Gson gson = new Gson();
        String json = gson.toJson(tags);
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("tags", json);
        return this.fetch("POST", "profiles/" + username + "/sources", data);
    }

    /**
     * Deletes a source given the source name
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, int sourceId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId);
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
