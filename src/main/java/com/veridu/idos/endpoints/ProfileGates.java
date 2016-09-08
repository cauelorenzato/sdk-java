package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

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
    public ProfileGates(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
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
        JsonObject data = new JsonObject();
        data.addProperty("name", gateName);
        data.addProperty("pass", String.valueOf(pass));
        return this.fetch("POST", "profiles/" + username + "/gates", data);
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
        JsonObject data = new JsonObject();
        data.addProperty("pass", pass);
        return this.fetch("PUT", "profiles/" + username + "/gates/" + gateName, data);
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
