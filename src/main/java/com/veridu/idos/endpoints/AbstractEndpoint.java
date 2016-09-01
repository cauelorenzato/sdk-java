package com.veridu.idos.endpoints;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.veridu.idos.CompanyFactory;
import com.veridu.idos.CredentialFactory;
import com.veridu.idos.exceptions.SDKException;
import com.veridu.idos.settings.Config;

public abstract class AbstractEndpoint {

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
     * Class constructor
     */
    public AbstractEndpoint() {

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
        return fetch(method, resource, "");
    }

    /**
     * Fetches an API Resource
     *
     * @param method
     *            String
     * @param resource
     *            String
     * @param data
     *            String
     *
     * @throws SDKException
     *
     */
    public JsonObject fetch(String method, String resource, String data) throws SDKException {
        String url = this.transformURL(method, resource, data);
        JsonObject response = request(method, url, data);

        return response;
    }

    public String transformURL(String method, String resource, String data) {
        String url = Config.BASE_URL;
        if (resource.charAt(0) != '/')
            url = url.concat("/");
        url = url.concat(resource);

        if ((method.compareTo("GET") == 0) && (data != null) && (!data.isEmpty())) {
            if (url.contains("?"))
                url = url.concat("&");
            else
                url = url.concat("?");
            url = url.concat(data);
        }

        return url;
    }

    /**
     * Method that converts Hash Table data to an encoded (UTF-8) String.
     *
     * @param data
     *
     * @return String dataAsString
     */
    protected String queryBuilder(HashMap<String, String> data) throws UnsupportedEncodingException {
        if (data.isEmpty())
            return "";
        StringBuilder dataAsString = new StringBuilder();
        for (Entry<String, String> entry : data.entrySet()) {
            if (dataAsString.length() != 0) {
                dataAsString.append("&");
            }
            dataAsString.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            dataAsString.append("=");
            dataAsString.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return dataAsString.toString();
    }

    /**
     * Method that converts Hash Table data to an encoded (UTF-8) String.
     *
     * @param key
     * @param value
     *
     * @return String data
     *
     * @throws UnsupportedEncodingException
     */
    protected String queryBuilder(String key, String value) throws UnsupportedEncodingException {
        String data = "";
        data = data.concat(URLEncoder.encode(key, "UTF-8"));
        data = data.concat("=");
        data = data.concat(URLEncoder.encode(value, "UTF-8"));
        return data;
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
    public JsonObject request(String method, String url, String data) {
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            String tokenType = this.getEndpointName();

            if (tokenType == "companies") {
                connection.setRequestProperty("Authorization", "CompanyToken " + CompanyFactory.token);
            } else if (tokenType == "users") {
                connection.setRequestProperty("Authorization", "CredentialToken " + CredentialFactory.token);
            }
            // else {
            // connection.setRequestProperty("Authorization", "CredentialToken "
            // + ManagementFactory.token);
            // }

            if ((method.compareTo("GET") != 0) && (data != null) && (!data.isEmpty())) {
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
                connection.setRequestProperty("Cache-Control", "no-cache");
                connection.setDoInput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(data);
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

    protected JsonObject convertToJson(String apiResponse) {
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(apiResponse);
        JsonObject json = response.getAsJsonObject();

        return json;
    }

    protected String getEndpointName() {
        String packageName = this.getClass().getPackage().getName();
        String tokenType = null;
        switch (packageName) {
        case "com.veridu.idos.endpoints.companies":
            tokenType = "companies";
            break;
        case "com.veridu.idos.endpoints.management":
            tokenType = "credentials";
            break;
        case "com.veridu.idos.endpoints.profiles":
            tokenType = "users";
            break;
        }

        return tokenType;
    }
}
