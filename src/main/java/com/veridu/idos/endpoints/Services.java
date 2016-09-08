package com.veridu.idos.endpoints;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

/**
 * Services Endpoint Class
 * 
 * @version 2.0
 *
 */
public class Services extends AbstractEndpoint {

    /**
     * Constructor Class
     */
    public Services(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.MANAGEMENT);
    }

    /**
     * Lists all services associated to the company token
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "services");
    }

    /**
     * Retrieves the service given the serviceId
     * 
     * @param serviceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(int serviceId) throws SDKException {
        return this.fetch("GET", "services/" + serviceId);
    }

    /**
     * Creates a new service
     * 
     * @param name
     * @param url
     * @param enabled
     * @param access
     * @param authUsername
     * @param authPassword
     * @param listens
     * @param triggers
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String name, String url, boolean enabled, int access, String authUsername,
            String authPassword, ArrayList<String> listens, ArrayList<String> triggers) throws SDKException {

        JsonObject json = new JsonObject();

        JsonArray listensArray = new JsonArray();
        for (String list : listens)
            listensArray.add(list);

        JsonArray triggersArray = new JsonArray();
        for (String trigger : triggers)
            triggersArray.add(trigger);

        json.addProperty("name", name);
        json.addProperty("url", url);
        json.addProperty("enabled", enabled);
        json.addProperty("access", access);
        json.addProperty("auth_username", authUsername);
        json.addProperty("auth_password", authPassword);

        json.add("listens", listensArray);
        json.add("triggers", triggersArray);

        return this.fetch("POST", "services", json);
    }

    /**
     * Updates an existing service given its serviceId
     * 
     * @param serviceId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject update(int serviceId, HashMap<String, Object> data) throws SDKException {
        JsonObject json = new JsonObject();
        if (data.containsKey("name"))
            json.addProperty("name", (String) data.get("name"));

        if (data.containsKey("url"))
            json.addProperty("url", (String) data.get("url"));

        if (data.containsKey("enabled"))
            json.addProperty("enabled", (boolean) data.get("enabled"));

        if (data.containsKey("access"))
            json.addProperty("access", (int) data.get("access"));

        if (data.containsKey("auth_username"))
            json.addProperty("auth_username", (String) data.get("auth_username"));

        if (data.containsKey("auth_password"))
            json.addProperty("auth_password", (String) data.get("auth_password"));

        return this.fetch("PUT", "services/" + serviceId, json);
    }

    /**
     * Deletes a service given
     * 
     * @param serviceId
     * @return
     * @throws SDKException
     */
    public JsonObject delete(int serviceId) throws SDKException {
        return this.fetch("DELETE", "services/" + serviceId);
    }

    /**
     * Deletes all services related to the company token
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll() throws SDKException {
        return this.fetch("DELETE", "services");
    }
}
