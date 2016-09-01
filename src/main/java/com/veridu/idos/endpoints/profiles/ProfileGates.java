package com.veridu.idos.endpoints.profiles;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Profile Gates Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileGates extends AbstractEndpoint {
    /**
     * Class constructor
     */
    public ProfileGates() {

    }

    /**
     * Lists all gates given an username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/gates");
    }

    /**
     * Retrieves a gate given its gate name
     * 
     * @param username
     * @param gateName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String gateName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/gates/" + gateName);
    }

    /**
     * Creates a gate passing the gate name
     * 
     * @param username
     * @param gateName
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String gateName, boolean pass)
            throws SDKException, UnsupportedEncodingException {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", gateName);
        data.put("pass", String.valueOf(pass));
        return this.fetch("POST", "profiles/" + username + "/gates", this.queryBuilder(data));
    }

    /**
     * Updates a gate given its gate name
     * 
     * @param username
     * @param gateName
     * @param pass
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, String gateName, boolean pass)
            throws SDKException, UnsupportedEncodingException {
        return this.fetch("PUT", "profiles/" + username + "/gates/" + gateName,
                this.queryBuilder("pass", String.valueOf(pass)));
    }

    /**
     * Deletes a gate given the gate name
     * 
     * @param username
     * @param gateName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String gateName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/gates/" + gateName);
    }

    /**
     * Deletes all gates associated with a username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/gates");
    }
}
