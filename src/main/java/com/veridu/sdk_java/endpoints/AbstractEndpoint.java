package com.veridu.sdk_java.endpoints;

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
import com.veridu.sdk_java.CompanyFactory;
import com.veridu.sdk_java.ManagementFactory;
import com.veridu.sdk_java.exceptions.SDKException;
import com.veridu.sdk_java.settings.Config;

public class AbstractEndpoint {

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

        if (url.contains("?"))
            url = url.concat("&");
        else
            url = url.concat("?");

        if (this.getClass().getSimpleName().equals("Companies")
                || (this.getClass().getSimpleName().equals("Permissions"))) {
            url = url.concat("companyPrivKey=" + CompanyFactory.privateKey);
        } else {
            url = url.concat("credentialToken=" + ManagementFactory.token);
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
            this.setConnectionSettings(url, method);
            this.sendRequest(method, data);
            return this.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (this.connection != null)
                this.connection.disconnect();
        }
        return null;
    }

    protected void setConnectionSettings(String url, String method) throws IOException {
        URL requestUrl = new URL(url);
        this.connection = (HttpURLConnection) requestUrl.openConnection();
        this.connection.setRequestMethod(method);
        this.connection.setConnectTimeout(10000);
        this.connection.setReadTimeout(10000);
        this.connection.setUseCaches(false);
        this.connection.setDoOutput(true);
    }

    protected void sendRequest(String method, String data) throws IOException {
        if ((method.compareTo("GET") != 0) && (data != null) && (!data.isEmpty())) {
            this.connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            this.connection.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
            this.connection.setRequestProperty("Cache-Control", "no-cache");
            this.connection.setDoInput(true);
            DataOutputStream wr = new DataOutputStream(this.connection.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        }
    }

    protected JsonObject getResponse() throws IOException {
        this.lastCode = this.connection.getResponseCode();
        InputStream is;
        if (this.lastCode >= 400)
            is = this.connection.getErrorStream();
        else
            is = this.connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return this.convertToJson(response.toString());
    }

    protected JsonObject convertToJson(String apiResponse) {
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(apiResponse);
        JsonObject json = response.getAsJsonObject();

        return json;
    }
}
