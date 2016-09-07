package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.SDKException;

/**
 * Token Endpoint class
 * 
 * @version 2.0
 *
 */
public class Token extends AbstractEndpoint {

    /**
     * Constructor Class
     */
    public Token(String token) {
        super(token);
    }

    /**
     * Exchanges out user companyToken by a company companyToken.
     * 
     * @param token
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject exchangeToken(String token) throws SDKException, UnsupportedEncodingException {
        JsonObject data = new JsonObject();
        data.addProperty("slug", token);
        return this.fetch("POST", "token", data);
    }
}
