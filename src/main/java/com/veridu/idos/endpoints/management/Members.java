package com.veridu.idos.endpoints.management;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Members Class
 * 
 * @version 2.0
 */
public class Members extends AbstractEndpoint {

    /**
     * Class constructor
     */

    public Members() {
        super();
    }

    /**
     * Lists all members of a company
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "management/members");
    }

    /**
     * Retrieves a member given its id
     * 
     * @param id
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(int id) throws SDKException {
        return this.fetch("GET", "management/members/" + id);
    }

    /**
     * Creates a new Member for the given credential
     * 
     * @param credentialPubKey
     * @param userName
     * @param role
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String credentialPubKey, String userName, String role)
            throws SDKException, UnsupportedEncodingException {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("credential", credentialPubKey);
        data.put("userName", userName);
        data.put("role", role);
        return this.fetch("POST", "management/members", this.queryBuilder(data));
    }

    /**
     * Updates a member given its id being able to change the role
     * 
     * @param id
     * @param role
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(int id, String role) throws SDKException, UnsupportedEncodingException {
        return this.fetch("PUT", "management/members/" + id, this.queryBuilder("role", role));
    }

    /**
     * Deletes a member given its id
     * 
     * @param id
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject delete(int id) throws SDKException {
        return this.fetch("DELETE", "management/members/" + id);
    }

    /**
     * Deletes all members associated with the company private key given
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll() throws SDKException {
        return this.fetch("DELETE", "management/members");
    }
}
