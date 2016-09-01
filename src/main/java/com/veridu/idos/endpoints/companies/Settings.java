package com.veridu.idos.endpoints.companies;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Settings Class
 * 
 * @version 2.0
 */
public class Settings extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public Settings() {
        super();
    }

    /**
     * Lists all Settings for the given credential token
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "management/settings");
    }

    /**
     * Gets the setting for the given public key
     * 
     * @param id
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(int id) throws SDKException {
        return this.fetch("GET", "management/settings/" + id);
    }

    /**
     * Creates a new setting given the section, property and value
     * 
     * @param section
     * @param property
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String section, String property, String value)
            throws SDKException, UnsupportedEncodingException {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("section", section);
        data.put("property", property);
        data.put("value", value);

        return this.fetch("POST", "management/settings", this.queryBuilder(data));
    }

    /**
     * Updates an existing setting given the value and the public key of the
     * setting
     * 
     * @param value
     * @param id
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String value, int id) throws SDKException, UnsupportedEncodingException {
        return this.fetch("PUT", "management/settings/" + id, this.queryBuilder("value", value));
    }

    /**
     * Deletes an existing setting given the public key of the setting
     * 
     * @param id
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(int id) throws SDKException {
        return this.fetch("DELETE", "management/settings/" + id);
    }

    /**
     * Deletes all settings associated with the credential token
     * 
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll() throws SDKException {
        return this.fetch("DELETE", "management/settings");
    }
}
