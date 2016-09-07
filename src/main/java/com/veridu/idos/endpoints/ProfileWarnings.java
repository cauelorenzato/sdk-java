package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
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
     * Retrieves the warn related to the given warning slug
     * 
     * @param username
     * @param warningSlug
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String warningSlug) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/warnings/" + warningSlug);
    }

    /**
     * Creates a new warning related to the username given
     * 
     * @param username
     * @param name
     * @param reference
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String name, String reference) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("reference", reference);

        return this.fetch("POST", "profiles/" + username + "/warnings", data);
    }

    /**
     * Deletes a warning given its warning slug
     * 
     * @param username
     * @param warningSlug
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String warningSlug) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/warnings/" + warningSlug);
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
}
