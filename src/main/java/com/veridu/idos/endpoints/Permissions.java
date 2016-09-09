package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

/**
 * Permissions Class
 * 
 * @version 2.0
 */
public class Permissions extends AbstractEndpoint {

    /**
     * Class Constructor
     * 
     */
    public Permissions(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.MANAGEMENT);
    }

    /**
     * Lists all permissions for the given company slug
     * 
     * @param slug
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String slug) throws SDKException {
        return this.fetch("GET", "companies/" + slug + "/permissions");
    }

    /**
     * Retrieves the permission detail for the given permission route name and
     * slug
     * 
     * @param slug
     * @param permissionRouteName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String slug, String permissionRouteName) throws SDKException {
        return this.fetch("GET", "companies/" + slug + "/permissions/" + permissionRouteName);
    }

    /**
     * Creates a new Permission given the route name and slug
     * 
     * @param slug
     * @param routeName
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String slug, String permissionRouteName)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("routeName", permissionRouteName);
        
        return this.fetch("POST", "companies/" + slug + "/permissions", data);
    }

    /**
     * Deletes a Permission given the route name and slug
     * 
     * @param slug
     * @param permissionRouteName
     * @return
     * @throws SDKException
     */
    public JsonObject delete(String slug, String permissionRouteName) throws SDKException {
        return this.fetch("DELETE", "companies/" + slug + "/permissions/" + permissionRouteName);
    }

    /**
     * Deletes all permissions related to the slug
     * 
     * @param slug
     * @return
     * @throws SDKException
     */
    public JsonObject deleteAll(String slug) throws SDKException {
        return this.fetch("DELETE", "companies/" + slug + "/permissions");
    }
}
