package com.veridu.idos.endpoints;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;
import com.veridu.idos.utils.Filter;
import com.veridu.idos.utils.IdOSAuthType;
import com.veridu.idos.utils.IdOSUtils;

public abstract class AbstractEndpoint {

    /**
     * IdOSAuthType (USER, HANDLER, MANAGEMENT)
     */
    protected IdOSAuthType authType = null;
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
    private String currentToken = null;
    /**
     * Keys (public and private) necessary to generate UserToken,
     * CredentialToken, IdentityToken
     */
    private HashMap<String, String> credentials;

    /**
     * Class constructor
     */
    public AbstractEndpoint(HashMap<String, String> credentials, IdOSAuthType authType) throws InvalidToken {
        this.credentials = credentials;
        this.authType = authType;
    }

    /**
     * Fetches an API Resource
     * 
     * @param method
     *            String
     * @param resource
     *            String
     * @throws SDKException
     *
     */
    protected JsonObject fetch(String method, String resource) throws SDKException {
        return fetch(method, resource, null, null);
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
     * @throws SDKException
     */
    protected JsonObject fetch(String method, String resource, JsonObject data) throws SDKException {
        return fetch(method, resource, data, null);
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
    protected JsonObject fetch(String method, String resource, JsonObject data, Filter filter) throws SDKException {
        String url = this.transformURL(method, resource, filter);
        JsonObject response = request(method, url, data, filter);

        return response;
    }

    private String transformURL(String method, String resource, Filter filter) {
        String url = Config.BASE_URL;
        if (resource.charAt(0) != '/')
            url = url.concat("/");
        url = url.concat(resource);
        if (filter != null)
            if (filter.getParams().toString().isEmpty() == false) {
                url += "?" + filter.toString();
            }

        return url;
    }

    private String transformURL(String url, Filter filter) {
        if (filter != null) {
            if (url.contains("?") == false)
                url += "?" + filter.toString();
            else
                url += "&" + filter.toString();
        }
        return url;
    }

    /**
     * Regenerate the token
     * 
     * @throws InvalidToken
     */
    public void refreshToken() throws InvalidToken {
        this.generateAuthToken();
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
    public JsonObject request(String method, String url, JsonObject data, Filter filter) throws InvalidToken {
        JsonObject json = new JsonObject();
        JsonArray array = new JsonArray();
        if (this.currentToken == null) {
            this.generateAuthToken();
        }
        if (filter != null) {
            if (filter.getAllPagesTrue() == true) {
                String newUrl = "";
                do {
                    if (newUrl.length() != 0)
                        json = this.sendRequest(method, newUrl, data);
                    else
                        json = this.sendRequest(method, url, data);
                    array.addAll(json.get("data").getAsJsonArray());
                    int page = json.get("pagination").getAsJsonObject().get("current_page").getAsInt() + 1;
                    filter.addParameterByKeyName("page", String.valueOf(page));
                    newUrl = this.transformURL(url, filter);
                } while (json.get("pagination").getAsJsonObject().get("current_page").getAsInt() < json
                        .get("pagination").getAsJsonObject().get("total").getAsInt());
                json.add("data", array);
                return json;
            }
        }

        return this.sendRequest(method, url, data);
    }

    /**
     * Sends the request
     * 
     * @param method
     * @param url
     * @param data
     * @return
     */
    private JsonObject sendRequest(String method, String url, JsonObject data) {
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            if (this.authType.toString().equals("MANAGEMENT"))
                connection.setRequestProperty("Authorization", "CompanyToken " + this.currentToken);
            else if (this.authType.toString().equals("HANDLER"))
                connection.setRequestProperty("Authorization", "CredentialToken " + this.currentToken);
            else if (this.authType.toString().equals("USER"))
                connection.setRequestProperty("Authorization", "UserToken " + this.currentToken);

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
        if ((this.authType == null) || (this.currentToken == null) || (authType != this.authType)) {
            this.authType = authType;
            this.generateAuthToken();
        }
    }

    /**
     * Generates an authorization token
     * 
     * @throws InvalidToken
     */
    private String generateAuthToken() throws InvalidToken {
        switch (this.authType) {
        case USER:
            this.currentToken = IdOSUtils.generateUserToken(this.credentials.get("credentialPrivateKey"),
                    this.credentials.get("credentialPublicKey"), this.credentials.get("username"));
            break;
        case MANAGEMENT:
            this.currentToken = IdOSUtils.generateManagementToken(this.credentials.get("companyPrivateKey"),
                    this.credentials.get("companyPublicKey"));
            break;
        case HANDLER:
            System.out.println(this.credentials);
            this.currentToken = IdOSUtils.generateHandlerToken(this.credentials.get("servicePrivateKey"),
                    this.credentials.get("servicePublicKey"), this.credentials.get("credentialPublicKey"));
            break;
        case NONE:
            this.currentToken = "none";
            break;
        default:
            throw new InvalidToken();
        }

        return this.currentToken;
    }

    /**
     * Sets Credentials (public and private keys)
     * 
     * @param credentials
     *            HashMap<String, String>
     */
    public void setCredentials(HashMap<String, String> credentials) {
        this.credentials = credentials;
    }
}
