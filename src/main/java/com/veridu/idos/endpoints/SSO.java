package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * SSO Endpoint Class
 * 
 * @version 2.0
 */
public class SSO extends AbstractEndpoint {

    /**
     * Constructor Class
     * 
     * @param credentials
     * @throws InvalidToken
     */
    public SSO(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.NONE);
    }

    /**
     * Creates a new SSO provider
     * 
     * @param providerName
     * @param credentialPublicKey
     * @param accessToken
     * @return JsonObject
     * @throws SDKException
     */
    public JsonObject create(String providerName, String credentialPublicKey, String accessToken) throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("providerName", providerName);
        data.addProperty("credentialPubKey", credentialPublicKey);
        data.addProperty("accessToken", accessToken);

        return this.fetch("POST", "sso", data);
    }

    /**
     * Retrieves the status of a sso provider given its provider name
     * 
     * @param providerName
     * @return JsonObject
     * @throws SDKException
     */
    public JsonObject getOne(String providerName) throws SDKException {
        return this.fetch("GET", "sso/" + providerName);
    }
}
