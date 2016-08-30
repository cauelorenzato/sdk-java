package com.veridu.idos.endpoints.profiles;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Profile Attributes Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileAttributes extends AbstractEndpoint {
    @FIXME (not implemented all
    methods yet)

    /**
     * Class Constructor
     */
    public ProfileAttributes() {

    }

    /**
     * Lists all profiles attributes
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/attributes");
    }

    /**
     * Retrieves an attribute given its attribute name
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String attributeName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/attributes" + attributeName);
    }

    /**
     * Creates a new attribute passing the name of the attribute
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, String attributeName) throws SDKException {
        return this.fetch("POST", "profiles/" + username + "/attributes/" + attributeName);
    }

    /**
     * Deletes an attribute given its attributeName
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String attributeName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/attributes/" + attributeName);
    }

}
