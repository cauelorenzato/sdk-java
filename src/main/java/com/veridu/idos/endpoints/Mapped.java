package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;

/**
 * Role Acess Endpoint Class
 * 
 * @version 2.0
 *
 */
public class Mapped extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public Mapped() {

    }

    /**
     * Lists all data mapped by a given source
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, int sourceId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/mapped");
    }

    /**
     * Retrieves a mapped data from a given source.
     * 
     * @param username
     * @param sourceId
     * @param mappedName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int sourceId, String mappedName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/mapped/" + mappedName);
    }

    /**
     * Creates a new mapped data for the given source
     * 
     * @param username
     * @param sourceId
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, int sourceId, String name, String value)
            throws SDKException, UnsupportedEncodingException {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("value", value);

        return this.fetch("POST", "profiles/" + username + "/sources/" + sourceId + "/mapped", this.queryBuilder(data));
    }

    /**
     * Updates a mapped data in the given source.
     * 
     * @param username
     * @param sourceId
     * @param mappedName
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, int sourceId, String mappedName, String name, String value)
            throws SDKException, UnsupportedEncodingException {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("value", value);

        return this.fetch("PUT", "profiles/" + username + "/sources/" + sourceId + "/mapped/" + mappedName,
                this.queryBuilder(data));
    }

    /**
     * Deletes a mapped data from the given source.
     * 
     * @param username
     * @param sourceId
     * @param mappedName
     * @return
     * @throws SDKException
     */
    public JsonObject delete(String username, int sourceId, String mappedName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/mapped/" + mappedName);
    }

    /**
     * Deletes all mapped data from the given source.
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, int sourceId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/mapped");
    }

}
