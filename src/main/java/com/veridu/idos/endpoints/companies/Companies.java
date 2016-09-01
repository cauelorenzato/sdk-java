package com.veridu.idos.endpoints.companies;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.EmptyPrivateKey;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;

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
    public Companies() throws InvalidToken {
        super();
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
        String data = this.queryBuilder("name", name);
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
        String data = this.queryBuilder("name", name);
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
