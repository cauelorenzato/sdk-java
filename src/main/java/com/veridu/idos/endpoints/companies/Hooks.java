package com.veridu.idos.endpoints.companies;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Hooks Endpoint Class
 * 
 * @version 2.0
 */
public class Hooks extends AbstractEndpoint {

    /**
     * Class Constructor
     */
    public Hooks() {

    }

    /**
     * Lists all hooks available given the credential public key
     * 
     * @param credentialPubKey
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String credentialPubKey) throws SDKException {
        return this.fetch("GET", "management/credentials/" + credentialPubKey + "/hooks");
    }

    /**
     * Retrieves a hook given its id
     * 
     * @param credentialPubKey
     * @param id
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String credentialPubKey, int id) throws SDKException {
        return this.fetch("GET", "management/credentials/" + credentialPubKey + "/hooks/" + id);
    }

    /**
     * Creates a new hook given the credential public key, the trigger, url and
     * if is subscribedibed
     * 
     * @param credentialPubKey
     * @param trigger
     * @param url
     * @param subscribedibed
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String credentialPubKey, String trigger, String url, boolean subscribed)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("trigger", trigger);
        data.addProperty("url", url);
        data.addProperty("subscribedibed", subscribed);

        return this.fetch("POST", "management/credentials/" + credentialPubKey + "/hooks", data);
    }

    /**
     * Updates an existing hook given its id
     * 
     * @param credentialPubKey
     * @param id
     * @param trigger
     * @param url
     * @param subscribedibed
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String credentialPubKey, int id, String trigger, String url, boolean subscribed)
            throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("trigger", trigger);
        data.addProperty("url", url);
        data.addProperty("subscribedibed", subscribed);

        return this.fetch("PUT", "management/credentials/" + credentialPubKey + "/hooks/" + id, data);
    }

    /**
     * Deletes a hook given its id
     * 
     * @param credentialPubKey
     * @param id
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String credentialPubKey, int id) throws SDKException {
        return this.fetch("DELETE", "management/credentials/" + credentialPubKey + "/hooks/" + id);
    }

    /**
     * Deletes all hooks associated with the credential public key
     * 
     * @param credentialPubKey
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject deleteAll(String credentialPubKey) throws SDKException {
        return this.fetch("DELETE", "management/credentials/" + credentialPubKey);
    }

}
