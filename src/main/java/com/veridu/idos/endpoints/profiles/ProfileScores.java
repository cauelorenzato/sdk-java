package com.veridu.idos.endpoints.profiles;

import java.io.UnsupportedEncodingException;

import com.google.gson.JsonObject;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.SDKException;

/**
 * Profile Scores Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileScores extends AbstractEndpoint {

    public ProfileScores() {

    }

    /**
     * Lists all scores for the given attribute name
     * 
     * @param username
     * @param attributeName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, String attributeName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/attributes/" + attributeName + "/scores");
    }

    /**
     * Retrieves the score related to the given scoreName
     * 
     * @param username
     * @param attributeName
     * @param scoreName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, String attributeName, String scoreName) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/attributes/" + attributeName + "/scores/" + scoreName);
    }

    /**
     * Creates a new score
     * 
     * @param username
     * @param attributeName
     * @param scoreName
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject create(String username, String attributeName, String scoreName, double value)
            throws SDKException, UnsupportedEncodingException {
        String data = this.queryBuilder("name", scoreName);
        return this.fetch("POST", "profiles/" + username + "/attributes/" + attributeName + "/scores",
                data.concat("&value=" + value));
    }

    /**
     * Updates an existing score given the score name
     * 
     * @param username
     * @param attributeName
     * @param scoreName
     * @param value
     * @return JsonObject response
     * @throws SDKException
     * @throws UnsupportedEncodingException
     */
    public JsonObject update(String username, String attributeName, String scoreName, double value)
            throws SDKException, UnsupportedEncodingException {
        String data = this.queryBuilder("name", scoreName);
        return this.fetch("PUT", "profiles/" + username + "/attributes/" + attributeName + "/scores/" + scoreName,
                data.concat("&value=" + value));
    }

    /**
     * Deletes a score given its score name
     * 
     * @param username
     * @param attributeName
     * @param scoreName
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject delete(String username, String attributeName, String scoreName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/attributes/" + attributeName + "/scores/" + scoreName);
    }

    /**
     * Deletes all scores related to the attribute Name
     * 
     * @param username
     * @param attributeName
     * @return
     * @throws SDKException
     */
    public JsonObject deleteAll(String username, String attributeName) throws SDKException {
        return this.fetch("DELETE", "profiles/" + username + "/attributes/" + attributeName + "/scores");
    }

}
