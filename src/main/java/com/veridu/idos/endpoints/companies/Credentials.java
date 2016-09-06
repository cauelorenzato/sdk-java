package com.veridu.idos.endpoints.companies;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Credentials Class
 * 
 * @version 2.0
 *
 */
public class Credentials extends AbstractEndpoint {

    /**
     * Class constructor
     */
    public Credentials(String token) {
        super(token);
    }

    /**
     * Lists all credentials available
     * 
     * @return JsonObject response
     * 
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "management/credentials");
    }

    /**
     * Gets the credential given the public key
     * 
     * @param publicKey
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String publicKey) throws SDKException {
        return this.fetch("GET", "management/credentials/" + publicKey);
    }

    /**
     * Creates a new credential
     * 
     * @param name
     * @param production
     * @return JsonObject response
     * @throws UnsupportedEncodingException
     * @throws SDKException
     */
    public JsonObject create(String name, String production) throws UnsupportedEncodingException, SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("production", production);

        return this.fetch("POST", "management/credentials", data);
    }

    /**
     * Updates the credential giving the public key and the new name for the
     * credential
     * 
     * @param name
     * @param publicKey
     * @return JsonObject response
     * @throws UnsupportedEncodingException
     * @throws SDKException
     */
    public JsonObject update(String name, String publicKey) throws UnsupportedEncodingException, SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        return this.fetch("PUT", "management/credentials/" + publicKey, data);
    }

    /**
     * Deletes the credential givin the public key
     * 
     * @param publicKey
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String publicKey) throws SDKException {
        return this.fetch("DELETE", "management/credentials/" + publicKey);
    }
}
