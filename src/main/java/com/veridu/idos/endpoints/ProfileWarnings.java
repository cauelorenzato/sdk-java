package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * ProfileWarnings Endpoint class
 * 
 * @version 2.0
 */
public class ProfileWarnings extends AbstractEndpoint {

    /**
     * Constructor class
     * 
     * @param token
     */
    public ProfileWarnings(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Lists all warnings given the username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/warnings");
    }

    /**
     * Lists all warnings given the username, with filtering
     * 
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, Filter filter) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/warnings", null, filter);
    }

    /**
     * Retrieves the warn related to the given warning slug
     * 
     * @param username
     * @param warningId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, Integer warningId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/warnings/" + warningId);
    }

    /**
     * Creates a new warning related to the username given
     * 
     * @param username
     * @param slug
     * @param attribute name of the attribute to which the warning refers to
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String slug, String attribute) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("slug", slug);
        data.addProperty("attribute", attribute);

        return this.fetch("POST", "profiles/" + username + "/warnings", data);
    }

    /**
     * Deletes a warning given its warning slug
     * 
     * @param username
     * @param warningId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, Integer warningId) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/warnings/" + warningId);
    }

    /**
     * Deletes all warnings related to the given username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/warnings");
    }

    /**
     * Deletes all warnings related to the given username, with filtering
     * 
     * @param username
     * @param filter
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, Filter filter) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/warnings", null, filter);
    }
}
