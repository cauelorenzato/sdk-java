package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

/**
 * Profile Raw Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileRaw extends AbstractEndpoint {

    /**
     * Class constructor
     * 
     * @param credentials
     * @throws InvalidToken
     */
    public ProfileRaw(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Lists all raw data related to the given source
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, int sourceId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/raw");
    }

    /**
     * Retrieves a raw data given its name
     * 
     * @param username
     * @param sourceId
     * @param collection
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int sourceId, String collection) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/sources/" + sourceId + "/raw/" + collection);
    }

    /**
     * Creates a new raw data
     * 
     * @param username
     * @param sourceId
     * @param collection
     * @param collectionData
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, int sourceId, String collection, String collectionData)
            throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("collection", collection);
        data.addProperty("data", collectionData);

        return this.fetch("POST", "profiles/" + username + "/sources/" + sourceId + "/raw", data);
    }

    /**
     * Updates a raw data given its collection (name)
     * 
     * @param username
     * @param sourceId
     * @param collection
     * @param updatedCollection
     * @param collectionData
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject update(String username, int sourceId, String collection, String updatedCollection,
            String collectionData) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("collection", collection);
        data.addProperty("data", collectionData);

        return this.fetch("PUT", "profiles/" + username + "/sources/" + sourceId + "/raw/" + collection, data);
    }

    /**
     * Deletes a raw data given its collection (name)
     * 
     * @param username
     * @param sourceId
     * @param collection
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, int sourceId, String collection) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/raw/" + collection);
    }

    /**
     * Deletes all raw data given the source id
     * 
     * @param username
     * @param sourceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, int sourceId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/sources/" + sourceId + "/raw");
    }
}
