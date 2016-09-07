package com.veridu.idos.endpoints;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.IdOSUtils;

public abstract class AbstractEndpoint {

    /**
     * IdOSAuthType enum
     */
    protected IdOSAuthType authType;
    /**
     * Company's slug necessary to make most of requests to the API
     */
    protected String companySlug = null;
    /**
     * Default connection
     */
    protected HttpURLConnection connection = null;
    /**
     * Last API response code
     */
    private int lastCode;

    /**
     * Token necessary to make requests to the API
     */
    private String token;

    private HashMap<String, String> credentials;

    /**
     * Class constructor
     */
    public AbstractEndpoint(HashMap<String, String> credentials, IdOSAuthType authType) throws InvalidToken {
        this.credentials = credentials;
        this.authType = authType;
        this.generateAuthToken();
    }

    /**
     *
     * @param method
     *            String
     * @param resource
     *            String
     * @throws SDKException
     *
     */
    public JsonObject fetch(String method, String resource) throws SDKException {
        return fetch(method, resource, null);
    }

    /**
     * Fetches an API Resource
     *
     * @param method
     *            String
     * @param resource
     *            String
     * @param data
     *            JsonObject
     *
     * @throws SDKException
     *
     */
    public JsonObject fetch(String method, String resource, JsonObject data) throws SDKException {
        String url = this.transformURL(method, resource);
        JsonObject response = request(method, url, data);

        return response;
    }

    public String transformURL(String method, String resource) {
        String url = Config.BASE_URL;
        if (resource.charAt(0) != '/')
            url = url.concat("/");
        url = url.concat(resource);

        return url;
    }

    /**
     * Process request to API
     *
     * @param method
     *            String
     * @param url
     *            String
     * @param data
     *            String
     *
     * @return String response
     *
     * @throws RequestFailed
     *             Exception
     */
    public JsonObject request(String method, String url, JsonObject data) {
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            if (this.authType.toString().equals("MANAGEMENT")) {
                connection.setRequestProperty("Authorization", "CompanyToken " + this.token);
            } else if (this.authType.toString().equals("HANDLER")) {
                connection.setRequestProperty("Authorization", "CredentialToken " + this.token);
            } else {
                connection.setRequestProperty("Authorization", "UserToken " + this.token);
            }
            if ((method.compareTo("GET") != 0) && (data != null) && (data.size() != 0)) {
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                connection.setRequestProperty("Content-Length", Integer.toString(data.size()));
                connection.setRequestProperty("Cache-Control", "no-cache");
                connection.setDoInput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                String content = data.toString();
                wr.writeBytes(content);
                wr.flush();
                wr.close();
            }

            this.lastCode = connection.getResponseCode();
            InputStream is;
            if (this.lastCode >= 400)
                is = connection.getErrorStream();
            else
                is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            return this.convertToJson(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (this.connection != null)
                this.connection.disconnect();
        }
        return null;
    }

    /**
     * Converts string response to json
     * 
     * @param apiResponse
     * @return JsonObject response
     */
    protected JsonObject convertToJson(String apiResponse) {
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(apiResponse);
        JsonObject json = response.getAsJsonObject();

        return json;
    }

    /**
     * Setter
     * 
     * @param authType
     */
    public void setAuthType(IdOSAuthType authType) throws InvalidToken {
        this.authType = authType;
        this.generateAuthToken();
    }

    private void generateAuthToken() throws InvalidToken {
        switch (this.authType) {
        case USER:
            this.token = IdOSUtils.generateUserToken(credentials.get("credentialPrivateKey"),
                    credentials.get("credentialPublicKey"), credentials.get("username"));
            break;
        case MANAGEMENT:
            this.token = IdOSUtils.generateManagementToken(credentials.get("companyPrivateKey"),
                    credentials.get("companyPublicKey"));
            break;
        case HANDLER:
            this.token = IdOSUtils.generateHandlerToken(credentials.get("servicePrivateKey"),
                    credentials.get("servicePublicKey"), credentials.get("credentialPublicKey"));
            break;
        default:
            throw new InvalidToken();
        }

    }

}
