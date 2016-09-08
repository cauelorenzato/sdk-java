package com.veridu.idos.endpoints;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.Filter;

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
     * Lists all tasks related to the username given the process id
     * 
     * @param username
     * @param processId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject listAll(String username, int processId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/processes/" + processId + "/tasks");
    }

    /**
     * Retrieves a task given its task id
     * 
     * @param username
     * @param taskId
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject getOne(String username, int processId, int taskId) throws SDKException {
        return this.fetch("GET", "profiles/" + username + "/processes/" + processId + "/tasks/" + taskId);
    }

    /**
     * Creates a new task
     * 
     * @param username
     * @param processId
     * @param name
     * @param event
     * @param running
     * @return JsonObject response
     * @throws SDKException
     */
    public JsonObject create(String username, int processId, String name, String event, boolean running)
            throws SDKException {
        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("event", event);
        data.addProperty("running", running);

        return this.fetch("POST", "profiles/" + username + "/processes/" + processId + "/tasks", data);
    }

    /**
     * Updates a task given its task id
     * 
     * @param username
     * @param taskId
     * @return JsonObject response+
     * @throws SDKException
     */
    public JsonObject update(String username, int processId, int taskId) throws SDKException {
        return this.fetch("PUT", "profiles/" + username + "/processes/" + processId + "/tasks/" + taskId);
    }
}
