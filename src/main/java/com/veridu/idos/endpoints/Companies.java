package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.EmptyPrivateKey;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

/**
 * Companies Class
 * 
 * @version 2.0
 */
public class Companies extends AbstractEndpoint {

    /**
     * Class Constructor
     * 
     * @throws EmptyPrivateKey
     */
    public Companies(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.MANAGEMENT);
    }

    /**
     * Lists all companies available
     * 
     * @return String response
     * 
     * @throws SDKException
     */
    public JsonObject listAll() throws SDKException {
        return this.fetch("GET", "companies");
    }

    /**
     * Retrieves the respective company for the given companySlug
     * 
     * @param String
     *            companySlug The company slug to retrieve data for
     * 
     * @return String response
     *
     * @throws SDKException
     */
    public JsonObject getOne(String companySlug) throws SDKException {
        return this.fetch("GET", "companies/" + companySlug);
    }

    /**
     * Creates a new company
     * 
     * @param name
     * 
     * @return String response
     * 
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String name) throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        return this.fetch("POST", "companies", data);
    }

    /**
     * Updates an existing company given the companySlug
     *
     * @param name
     * @param companySlug
     *
     * @return String response
     *
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String name, String companySlug) throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        return this.fetch("PUT", "companies/" + companySlug, data);
    }

    /**
     * Deletes an existing company given the company slug
     *
     * @param companySlug
     *
     * @return String response
     *
     * @throws SDKException
     */
    public JsonObject delete(String companySlug) throws SDKException {
        return this.fetch("DELETE", "companies/" + companySlug);
    }
}
