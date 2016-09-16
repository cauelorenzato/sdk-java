package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

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
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/raw");
    }

    /**
     * Lists all raw data related to the given source
     *
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/raw", null, filter);
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
        data.addProperty("sourceId", sourceId);

        return this.fetch("POST", "profiles/" + username + "/raw", data);
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

        return this.fetch("PATCH", "profiles/" + username + "/raw/" + sourceId, data);
    }
}
