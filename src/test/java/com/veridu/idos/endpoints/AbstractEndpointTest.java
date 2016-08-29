package com.veridu.idos.endpoints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gson.JsonObject;
import com.veridu.idos.CompanyFactory;
import com.veridu.idos.ManagementFactory;
import com.veridu.idos.endpoints.AbstractEndpoint;
import com.veridu.idos.exceptions.EmptyPrivateKey;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.exceptions.SDKException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AbstractEndpoint.class)
public class AbstractEndpointTest {

    @Test
    public void testFetch() throws SDKException {
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
        Mockito.when(abstractMock.transformURL("POST", "/companies", "name=Company"))
                .thenReturn("http://localhost:8000/index.php/1.0/companies&companyPrivKey=privKey");
        JsonObject json = new JsonObject();
        json.addProperty("dummy", "value");
        Mockito.when(abstractMock.request("POST",
                "http://localhost:8000/index.php/1.0/companies&companyPrivKey=privKey", "name=Company"))
                .thenReturn(json);
        Mockito.when(abstractMock.fetch("POST", "/companies", "name=Company")).thenCallRealMethod();
        assertTrue(abstractMock.fetch("POST", "/companies", "name=Company") instanceof JsonObject);
        assertSame(json, abstractMock.fetch("POST", "/companies", "name=Company"));
    }

    @Test
    public void testFetchEmptyData() throws SDKException {
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
        JsonObject json = new JsonObject();
        json.addProperty("dummy", "value");
        Mockito.when(abstractMock.transformURL("POST", "/companies", ""))
                .thenReturn("http://localhost:8000/index.php/1.0/companies&companyPrivKey=privKey");
        Mockito.when(abstractMock.request("POST",
                "http://localhost:8000/index.php/1.0/companies&companyPrivKey=privKey", "")).thenReturn(json);
        Mockito.when(abstractMock.fetch("POST", "/companies", "")).thenCallRealMethod();
        assertTrue(abstractMock.fetch("POST", "/companies", "") instanceof JsonObject);
        assertSame(json, abstractMock.fetch("POST", "/companies", ""));
    }

    @Test
    public void testTransformURLGETMethod() throws InvalidToken, EmptyPrivateKey {
        // passing the token trough the constructor so it can be stored in the
        // token String and used in the trasnformURL() method.
        ManagementFactory factory = new ManagementFactory("token");
        assertEquals("http://localhost:8000/index.php/1.0/management/credentials?credentialToken=token",
                factory.getCredential().transformURL("GET", "management/credentials", ""));
        assertEquals("http://localhost:8000/index.php/1.0/management/settings?credentialToken=token",
                factory.getSetting().transformURL("GET", "management/settings", ""));
        assertEquals("http://localhost:8000/index.php/1.0/management/members?credentialToken=token",
                factory.getMember().transformURL("GET", "management/members", ""));
        CompanyFactory cfactory = new CompanyFactory("privKey");
        assertEquals("http://localhost:8000/index.php/1.0/companies?companyPrivKey=privKey",
                cfactory.getCompany().transformURL("GET", "companies", ""));
        assertEquals("http://localhost:8000/index.php/1.0/companies/permissions?companyPrivKey=privKey",
                cfactory.getPermission().transformURL("GET", "companies/permissions", ""));
    }

    @Test
    public void testTransformURLPOSTMethod() throws InvalidToken, EmptyPrivateKey {
        ManagementFactory factory = new ManagementFactory("token");
        assertEquals("http://localhost:8000/index.php/1.0/management/settings?credentialToken=token",
                factory.getSetting().transformURL("POST", "management/settings", "data"));
        assertEquals("http://localhost:8000/index.php/1.0/management/settings?credentialToken=token",
                factory.getSetting().transformURL("POST", "management/settings", "data"));
        assertEquals("http://localhost:8000/index.php/1.0/management/members?credentialToken=token",
                factory.getMember().transformURL("POST", "management/members", "data"));
        CompanyFactory cfactory = new CompanyFactory("privKey");
        assertEquals("http://localhost:8000/index.php/1.0/companies?companyPrivKey=privKey",
                cfactory.getCompany().transformURL("POST", "companies", "data"));
        assertEquals("http://localhost:8000/index.php/1.0/companies/permissions?companyPrivKey=privKey",
                cfactory.getPermission().transformURL("POST", "companies/permissions", "data"));
    }

    @Test
    public void testQueryBuilderHashMapOfStringString() throws UnsupportedEncodingException {
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("key1", "value 1");
        data.put("key2", "value 2");
        data.put("key3", "value 3");
        assertEquals("key1=value+1&key2=value+2&key3=value+3", abstractMock.queryBuilder(data));

    }

    @Test
    public void testQueryBuilderStringString() throws UnsupportedEncodingException {
        AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);
        assertEquals("key=new+value", abstractMock.queryBuilder("key", "new value"));
    }

    @Test
    public void testRequestGETMethod() throws Exception {
        AbstractEndpoint endpoint = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);

        String method = "GET";
        String url = "http://idos.api/companies/company-slug";
        String data = "";

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
        String data = "name=New+ame";

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
