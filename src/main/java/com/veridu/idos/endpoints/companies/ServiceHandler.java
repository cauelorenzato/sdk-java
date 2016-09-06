package com.veridu.idos.endpoints.companies;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * ServiceHandler Endpoint class
 * 
 * @version 2.0
 */
public class ServiceHandler extends AbstractEndpoint {

    /**
     * Constructor Class
     */
    public ServiceHandler(String token) {
        super(token);
    }

    /**
     * Lists all service handlers related to the given company token
     * 
     * @return JsonObject response
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "service-handlers");
    }

    /**
     * Retrieves a service handler given its service handler id
     * 
     * @param serviceHandlerId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(int serviceHandlerId) throws SDKException {
        return this.fetch("GET", "service-handlers/" + serviceHandlerId);
    }

    /**
     * Creates a new service handler
     * 
     * @param serviceId
     * @param listens
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(int serviceId, ArrayList<String> listens) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("serviceId", serviceId);
        JsonArray listensArray = new JsonArray();
        for (String list : listens)
            listensArray.add(list);
        data.add("listens", listensArray);
        return this.fetch("POST", "service-handlers", data);
    }

    /**
     * Updates a service handler given its id
     * 
     * @param serviceHandlerId
     * @param listens
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject update(int serviceHandlerId, ArrayList<String> listens) throws SDKException {
        JsonObject data = new JsonObject();
        JsonArray listensArray = new JsonArray();
        for (String list : listens)
            listensArray.add(list);
        data.add("listens", listensArray);

        return this.fetch("PUT", "service-handlers/" + serviceHandlerId, data);
    }

    /**
     * Deletes a service handler given its id
     * 
     * @param serviceHandlerId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(int serviceHandlerId) throws SDKException {
        return this.fetch("DELETE", "service-handlers/" + serviceHandlerId);
    }

    /**
     * Deletes all service handlers related to the given company
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll() throws SDKException {
        return this.fetch("DELETE", "service-handlers");
    }
}
