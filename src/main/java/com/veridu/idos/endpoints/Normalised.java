package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * Role Acess Endpoint Class
 * 
 * @version 2.0
 *
 */
public class Normalised extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public Normalised(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Lists all data normalised by a given source
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, int sourceId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/normalised");
    }

    /**
     * Retrieves a normalised data from a given source.
     * 
     * @param username
     * @param sourceId
     * @param normalisedName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int sourceId, String normalisedName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/normalised/" + normalisedName);
    }

    /**
     * Creates a new normalised data for the given source
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
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);

        return this.fetch("POST", "profiles/" + username + "/sources/" + sourceId + "/normalised", data);
    }

    /**
     * Updates a normalised data in the given source.
     * 
     * @param username
     * @param sourceId
     * @param normalisedName
     * @param name
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, int sourceId, String normalisedName, String name, String value)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("value", value);

        return this.fetch("PUT", "profiles/" + username + "/sources/" + sourceId + "/normalised/" + normalisedName,
                data);
    }

    /**
     * Deletes a normalised data from the given source.
     * 
     * @param username
     * @param sourceId
     * @param normalisedName
     * @return
     * @throws SDKException
     */
    public JsonObject delete(String username, int sourceId, String normalisedName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/normalised/" + normalisedName);
    }

    /**
     * Deletes all normalised data from the given source.
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, int sourceId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/normalised");
    }

}
