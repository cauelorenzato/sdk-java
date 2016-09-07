package com.veridu.idos.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

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
    public Token(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.USER);
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
