package com.veridu.idos.endpoints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.JsonObject;
import com.veridu.idos.Factory;
import com.veridu.idos.exceptions.EmptyPrivateKey;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AbstractEndpoint.class)
public class AbstractEndpointTest {

    @Test
    public void testFetch() throws SDKException {
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
        JsonObject json = new JsonObject();
        json.addProperty("dummy", "value");
        Mockito.when(abstractMock.transformURL("POST", "/companies"))
                .thenReturn("http://localhost:8000/index.php/1.0/companies");
        Mockito.when(abstractMock.request("POST", "http://localhost:8000/index.php/1.0/companies", json))
                .thenReturn(json);
        Mockito.when(abstractMock.fetch("POST", "/companies", json)).thenCallRealMethod();
        assertSame(json, abstractMock.fetch("POST", "/companies", json));
    }

    @Test
    public void testFetchEmptyData() throws SDKException {
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
        JsonObject json = new JsonObject();
        Mockito.when(abstractMock.transformURL("POST", "/companies"))
                .thenReturn("http://localhost:8000/index.php/1.0/companies");
        Mockito.when(abstractMock.request("POST", "http://localhost:8000/index.php/1.0/companies", null))
                .thenReturn(json);
        Mockito.when(abstractMock.fetch("POST", "/companies", null)).thenCallRealMethod();
        assertSame(json, abstractMock.fetch("POST", "/companies", null));
    }

    @Test
    public void testTransformURLGETMethod() throws InvalidToken, EmptyPrivateKey {
        // passing the companyToken trough the constructor so it can be stored
        // in the
        // companyToken String and used in the trasnformURL() method.
        Factory factory = new Factory("companyToken");
        assertEquals("http://localhost:8000/index.php/1.0/profile/attributes",
                factory.getAttribute().transformURL("GET", "profile/attributes"));
        Factory cfactory = new Factory("privKey");
        assertEquals("http://localhost:8000/index.php/1.0/companies",
                cfactory.getCompany().transformURL("GET", "companies"));
        assertEquals("http://localhost:8000/index.php/1.0/companies/permissions",
                cfactory.getPermission().transformURL("GET", "companies/permissions"));
    }

    @Test
    public void testTransformURLPOSTMethod() throws InvalidToken, EmptyPrivateKey {
        Factory factory = new Factory("companyToken");
        assertEquals("http://localhost:8000/index.php/1.0/profile/attributes",
                factory.getAttribute().transformURL("POST", "profile/attributes"));
        Factory cfactory = new Factory("privKey");
        assertEquals("http://localhost:8000/index.php/1.0/companies",
                cfactory.getCompany().transformURL("POST", "companies"));
        assertEquals("http://localhost:8000/index.php/1.0/companies/permissions",
                cfactory.getPermission().transformURL("POST", "companies/permissions"));
    }

    @Test
    public void testRequestGETMethod() throws Exception {
        AbstractEndpoint endpoint = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);

        String method = "GET";
        String url = "http://idos.api/companies/company-slug";
        JsonObject data = new JsonObject();

        /**
         * We need PowerMockito because URL is a final Class
         */
        URL requestUrl = PowerMockito.mock(URL.class);
        HttpURLConnection conn = PowerMockito.mock(HttpURLConnection.class);
        InputStream is = PowerMockito.mock(InputStream.class);
        InputStreamReader isr = PowerMockito.mock(InputStreamReader.class);
        BufferedReader rd = PowerMockito.mock(BufferedReader.class);

        PowerMockito.whenNew(URL.class).withParameterTypes(String.class).withArguments(url).thenReturn(requestUrl);
        PowerMockito.whenNew(InputStreamReader.class).withArguments(is).thenReturn(isr);
        PowerMockito.whenNew(BufferedReader.class).withArguments(isr).thenReturn(rd);

        PowerMockito.when(requestUrl.openConnection()).thenReturn(conn);
        PowerMockito.doNothing().when(conn).setRequestMethod(method);
        PowerMockito.doNothing().when(conn).setConnectTimeout(10000);
        PowerMockito.doNothing().when(conn).setReadTimeout(10000);
        PowerMockito.doNothing().when(conn).setUseCaches(false);
        PowerMockito.doNothing().when(conn).setDoOutput(true);
        PowerMockito.when(conn.getResponseCode()).thenReturn(200);
        PowerMockito.when(conn.getInputStream()).thenReturn(is);
        PowerMockito.when(rd.readLine()).thenReturn("{\"status\":true}").thenReturn(null);
        JsonObject json = new JsonObject();
        json.addProperty("status", true);
        assertEquals(json, endpoint.request(method, url, data));
    }

    @Test
    public void testRequestPOSTMethod() throws Exception {
        AbstractEndpoint endpoint = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);
        String method = "POST";
        String url = "http://idos.api/companies/company-slug";
        JsonObject data = new JsonObject();
        data.addProperty("name", "name");

        /**
         * We need PowerMockito because URL is a final Class
         */
        URL requestUrl = PowerMockito.mock(URL.class);
        HttpURLConnection conn = PowerMockito.mock(HttpURLConnection.class);
        InputStream is = PowerMockito.mock(InputStream.class);
        InputStreamReader isr = PowerMockito.mock(InputStreamReader.class);
        BufferedReader rd = PowerMockito.mock(BufferedReader.class);
        DataOutputStream wr = PowerMockito.mock(DataOutputStream.class);

        PowerMockito.whenNew(URL.class).withParameterTypes(String.class).withArguments(url).thenReturn(requestUrl);
        PowerMockito.whenNew(InputStreamReader.class).withArguments(is).thenReturn(isr);
        PowerMockito.whenNew(BufferedReader.class).withArguments(isr).thenReturn(rd);
        PowerMockito.whenNew(DataOutputStream.class).withAnyArguments().thenReturn(wr);

        PowerMockito.when(requestUrl.openConnection()).thenReturn(conn);
        PowerMockito.doNothing().when(conn).setRequestMethod(method);
        PowerMockito.doNothing().when(conn).setConnectTimeout(10000);
        PowerMockito.doNothing().when(conn).setReadTimeout(10000);
        PowerMockito.doNothing().when(conn).setUseCaches(false);
        PowerMockito.doNothing().when(conn).setDoOutput(true);
        PowerMockito.when(conn.getResponseCode()).thenReturn(200);
        PowerMockito.when(conn.getInputStream()).thenReturn(is);
        PowerMockito.when(rd.readLine()).thenReturn("{\"status\":true}").thenReturn(null);
        PowerMockito.doNothing().when(wr).writeBytes(Mockito.anyString());
        JsonObject json = new JsonObject();
        json.addProperty("status", true);
        assertEquals(json, endpoint.request(method, url, data));
    }

    @Test
    public void testConvertToJson() throws Exception {
        AbstractEndpoint endpoint = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);
        JsonObject json = new JsonObject();
        json.addProperty("status", true);
        assertEquals(json, endpoint.convertToJson("{\"status\":true}"));
    }
}
