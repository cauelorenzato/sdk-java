package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

/**
 * Digested Endpoint Class
 * 
 * @version 2.0
 *
 */
public class Digested extends AbstractEndpoint {

    /**
     * Constructor Class
     */
    public Digested(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Retrieve a complete list of the data digested by a given source.
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, int sourceId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/digested");
    }
    
    /**
     * Retrieve a complete list of the data digested by a given source.
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, int sourceId, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/digested", null, filter);
    }

    /**
     * Retrieves a digested data from a given source.
     * 
     * @param username
     * @param sourceId
     * @param digestedName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int sourceId, String digestedName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/digested/" + digestedName);
    }

    /**
     * Creates a new digested data
     * 
     * @param username
     * @param sourceId
     * @param digestedName
     * @param digestedValue
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, int sourceId, String digestedName, String digestedValue)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", digestedName);
        data.addProperty("value", digestedValue);
        return this.fetch("POST", "profiles/" + username + "/sources/" + sourceId + "/digested", data);
    }

    /**
     * Updates a digested data
     * 
     * @param username
     * @param sourceId
     * @param digestedName
     * @param digestedValue
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, int sourceId, String digestedName, String digestedValue)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("value", digestedValue);
        return this.fetch("PUT", "profiles/" + username + "/sources/" + sourceId + "/digested/" + digestedName, data);
    }

    /**
     * Deletes a digested data given its name
     * 
     * @param username
     * @param sourceId
     * @param digestedName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, int sourceId, String digestedName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/digested/" + digestedName);
    }

    /**
     * Deletes all digested data related to the given sourceId
     * 
     * @param username
     * @param sourceIds
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, int sourceId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/digested");
    }

    /**
     * Deletes all digested data related to the given sourceId
     * 
     * @param username
     * @param sourceIds
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, int sourceId, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/digested", null, filter);
    }
}
