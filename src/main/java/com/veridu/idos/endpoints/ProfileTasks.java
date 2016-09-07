package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;

/**
 * Profile Tasks Endpoint Class
 * 
 * @version 2.0
 *
 */
public class ProfileTasks extends AbstractEndpoint {

    public ProfileTasks(HashMap<String, String> credentials) throws InvalidToken {
        super(credentials, IdOSAuthType.HANDLER);
    }

    /**
     * Lists all tasks related to the username
     * 
     * @param username
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/tasks");
    }

    /**
     * Retrieves a task given its task id
     * 
     * @param username
     * @param taskId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int taskId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/tasks/" + taskId);
    }

    /**
     * Updates a task given its task id
     * 
     * @param username
     * @param taskId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject update(String username, int taskId) throws SDKException {
        return this.fetch("PUT", "profiles/" + username + "/tasks/" + taskId);
    }
}
