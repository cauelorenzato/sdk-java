package com.veridu.idos.endpoints;

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
public class RoleAccess extends AbstractEndpoint {

    /**
     * Constructor class
     * 
     * @param credentials
     * @throws InvalidToken
     */
    public RoleAccess(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.USER);
    }

    /**
     * Lists all roles related to the User token
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "access/roles");
    }

    /**
     * Retrieves the role given its id
     * 
     * @param roleId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(int roleId) throws SDKException {
        return this.fetch("GET", "access/roles/" + roleId);
    }

    /**
     * Creates a new access role
     * 
     * @param roleName
     * @param roleAccess
     * @param resource
     * @return JsonObject Response
     * @throws SDKException
     */
    public JsonObject create(String roleName, int roleAccess, String resource) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("role", roleName);
        data.addProperty("access", roleAccess);
        data.addProperty("resource", resource);

        return this.fetch("POST", "access/roles", data);
    }

    /**
     * Updates access for a role, given its role id
     * 
     * @param roleId
     * @param roleAccess
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject update(int roleId, int roleAccess) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("access", roleAccess);
        return this.fetch("PUT", "access/roles/" + roleId, data);
    }

    /**
     * Deletes a role access given its role id
     * 
     * @param roleId
     * @return
     * @throws SDKException
     */
    public JsonObject delete(int roleId) throws SDKException {
        return this.fetch("DELETE", "access/roles" + roleId);
    }

    /**
     * Deletes all role access related to the User token
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll() throws SDKException {
        return this.fetch("DELETE", "access/roles");
    }

}
