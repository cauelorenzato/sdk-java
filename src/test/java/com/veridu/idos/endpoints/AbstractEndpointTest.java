// package com.veridu.idos.endpoints;
//
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertSame;
//
// import java.io.BufferedReader;
// import java.io.DataOutputStream;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.HashMap;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mockito;
// import org.powermock.api.mockito.PowerMockito;
// import org.powermock.core.classloader.annotations.PrepareForTest;
// import org.powermock.modules.junit4.PowerMockRunner;
// import org.powermock.reflect.Whitebox;
//
// import com.google.gson.JsonObject;
// import com.veridu.idos.IdOSAPIFactory;
// import com.veridu.idos.exceptions.InvalidToken;
// import com.veridu.idos.exceptions.SDKException;
// import com.veridu.idos.utils.Filter;
// import com.veridu.idos.utils.IdOSAuthType;
//
// @RunWith(PowerMockRunner.class)
// @PrepareForTest(AbstractEndpoint.class)
// public class AbstractEndpointTest {
//
// @Test
// public void testConstructor() throws InvalidToken {
// HashMap<String, String> credentials = new HashMap<>();
// credentials.put("credentialPrivateKey", "credentialPrivateKey");
// credentials.put("credentialPublicKey", "credentialPublicKey");
// credentials.put("servicePrivateKey", "servicePrivateKey");
// credentials.put("servicePublicKey", "servicePublicKey");
// ProfileTags tag = new ProfileTags(credentials);
// assertSame("MANAGEMENT", tag.authType.toString());
// }
//
// @Test
// public void testFetchEmptyData() throws SDKException {
// AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
// JsonObject json = new JsonObject();
// json.addProperty("response", "response");
// abstractMock.setAuthType(IdOSAuthType.MANAGEMENT);
// Mockito.when(abstractMock.request("GET", "http://127.0.0.1:8000/index.php/1.0/profiles", null))
// .thenReturn(json);
// Mockito.when(abstractMock.fetch("GET", "/profiles", null)).thenCallRealMethod();
// assertSame(json, abstractMock.fetch("GET", "/profiles", null));
// }
//
// @Test
// public void testFetch() throws SDKException {
// AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
// JsonObject json = new JsonObject();
// json.addProperty("data", "data");
// JsonObject response = new JsonObject();
// response.addProperty("response", "response");
// Mockito.when(abstractMock.request("POST", "http://127.0.0.1:8000/index.php/1.0/profiles", json))
// .thenReturn(response);
// Mockito.when(abstractMock.fetch("POST", "/profiles", json)).thenCallRealMethod();
// assertSame(response, abstractMock.fetch("POST", "/profiles", json));
// }
//
// @Test
// public void testFetchWithFilter() throws SDKException {
// Filter filter = Filter.createFilter();
// filter.addFilterByKeyName("filter", "filter");
// AbstractEndpoint abstractMock = Mockito.mock(AbstractEndpoint.class);
// JsonObject json = new JsonObject();
// json.addProperty("data", "data");
// JsonObject response = new JsonObject();
// response.addProperty("response", "response");
// Mockito.when(abstractMock.request("POST", "http://127.0.0.1:8000/index.php/1.0/profiles?filter=filter", json))
// .thenReturn(response);
// Mockito.when(abstractMock.fetch("POST", "/profiles", json, filter)).thenCallRealMethod();
// assertSame(response, abstractMock.fetch("POST", "/profiles", json, filter));
// }
//
// @Test
// public void testTransformURLGETMethod() throws Exception {
// HashMap<String, String> credentials = new HashMap<>();
// credentials.put("credentialPublicKey", "credentialPublicKey");
// credentials.put("creentialPrivateKey", "credentialPrivateKey");
//
// IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(credentials);
// ProfileAttributes attribute = idOSAPIFactory.getAttribute();
// Object[] params = { "GET", "profiles/attributes", null };
// assertEquals("http://127.0.0.1:8000/index.php/1.0/profiles/attributes",
// Whitebox.invokeMethod(attribute, "transformURL", params));
// }
//
// @Test
// public void testTransformURLPOSTMethod() throws Exception {
// HashMap<String, String> credentials = new HashMap<>();
// credentials.put("credentialPublicKey", "credentialPublicKey");
// credentials.put("creentialPrivateKey", "credentialPrivateKey");
// IdOSAPIFactory idOSAPIFactory = new IdOSAPIFactory(credentials);
// ProfileAttributes attribute = idOSAPIFactory.getAttribute();
// JsonObject data = new JsonObject();
// data.addProperty("json", "json");
// Filter filter = Filter.createFilter();
// filter.addFilterByKeyName("filter", "filter");
// Object[] params = { "POST", "profiles/attributes", filter };
// assertEquals("http://127.0.0.1:8000/index.php/1.0/profiles/attributes?filter=filter",
// Whitebox.invokeMethod(attribute, "transformURL", params));
// }
//
// @Test
// public void testRequestGETMethod() throws Exception {
// AbstractEndpoint endpoint = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);
// HashMap<String, String> credentials = new HashMap<>();
// String method = "GET";
// String url = "http://127.0.0.1:8000/index.php/1.0/profiles";
// JsonObject data = new JsonObject();
// data.addProperty("json", "json");
// credentials.put("credentialPrivateKey", "123456");
// credentials.put("credentialPublicKey", "654321");
// credentials.put("servicePublicKey", "123456");
// credentials.put("servicePrivateKey", "654321");
// Whitebox.setInternalState(endpoint, "credentials", credentials);
// Whitebox.setInternalState(endpoint, "authType", IdOSAuthType.HANDLER);
// Whitebox.setInternalState(endpoint, "currentToken", "token");
//
// /**
// * PowerMockito is necessary because URL is a final Class
// */
// URL requestUrl = PowerMockito.mock(URL.class);
// HttpURLConnection conn = PowerMockito.mock(HttpURLConnection.class);
// InputStream is = PowerMockito.mock(InputStream.class);
// InputStreamReader isr = PowerMockito.mock(InputStreamReader.class);
// BufferedReader rd = PowerMockito.mock(BufferedReader.class);
// PowerMockito.whenNew(URL.class).withParameterTypes(String.class).withArguments(url).thenReturn(requestUrl);
// PowerMockito.whenNew(InputStreamReader.class).withArguments(is).thenReturn(isr);
// PowerMockito.whenNew(BufferedReader.class).withArguments(isr).thenReturn(rd);
// PowerMockito.when(requestUrl.openConnection()).thenReturn(conn);
// PowerMockito.doNothing().when(endpoint).setAuthType(IdOSAuthType.HANDLER);
// PowerMockito.doNothing().when(conn).setRequestMethod(method);
// PowerMockito.doNothing().when(conn).setConnectTimeout(10000);
// PowerMockito.doNothing().when(conn).setReadTimeout(10000);
// PowerMockito.doNothing().when(conn).setUseCaches(false);
// PowerMockito.doNothing().when(conn).setDoOutput(true);
// PowerMockito.when(conn.getResponseCode()).thenReturn(200);
// PowerMockito.when(conn.getInputStream()).thenReturn(is);
// PowerMockito.when(rd.readLine()).thenReturn("{\"status\":true}").thenReturn(null);
// JsonObject json = new JsonObject();
// json.addProperty("status", true);
//
// assertEquals(json, endpoint.request(method, url, data));
// }
//
// @Test
// public void testRequestPOSTMethod() throws Exception {
// AbstractEndpoint endpoint = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);
// HashMap<String, String> credentials = new HashMap<>();
// String method = "POST";
// String url = "http://idos.api/companies/company-slug";
// JsonObject data = new JsonObject();
// data.addProperty("json", "json");
// credentials.put("credentialPrivateKey", "123456");
// credentials.put("credentialPublicKey", "654321");
// credentials.put("servicePublicKey", "123456");
// credentials.put("servicePrivateKey", "654321");
// Whitebox.setInternalState(endpoint, "credentials", credentials);
// Whitebox.setInternalState(endpoint, "authType", IdOSAuthType.HANDLER);
// Whitebox.setInternalState(endpoint, "currentToken", "token");
// /**
// * We need PowerMockito because URL is a final Class
// */
// URL requestUrl = PowerMockito.mock(URL.class);
// HttpURLConnection conn = PowerMockito.mock(HttpURLConnection.class);
// InputStream is = PowerMockito.mock(InputStream.class);
// InputStreamReader isr = PowerMockito.mock(InputStreamReader.class);
// BufferedReader rd = PowerMockito.mock(BufferedReader.class);
// DataOutputStream wr = PowerMockito.mock(DataOutputStream.class);
//
// PowerMockito.whenNew(URL.class).withParameterTypes(String.class).withArguments(url).thenReturn(requestUrl);
// PowerMockito.whenNew(InputStreamReader.class).withArguments(is).thenReturn(isr);
// PowerMockito.whenNew(BufferedReader.class).withArguments(isr).thenReturn(rd);
// PowerMockito.whenNew(DataOutputStream.class).withAnyArguments().thenReturn(wr);
//
// PowerMockito.when(requestUrl.openConnection()).thenReturn(conn);
// PowerMockito.doNothing().when(conn).setRequestMethod(method);
// PowerMockito.doNothing().when(conn).setConnectTimeout(10000);
// PowerMockito.doNothing().when(conn).setReadTimeout(10000);
// PowerMockito.doNothing().when(conn).setUseCaches(false);
// PowerMockito.doNothing().when(conn).setDoOutput(true);
// PowerMockito.when(conn.getResponseCode()).thenReturn(200);
// PowerMockito.when(conn.getInputStream()).thenReturn(is);
// PowerMockito.when(rd.readLine()).thenReturn("{\"status\":true}").thenReturn(null);
// PowerMockito.doNothing().when(wr).writeBytes(Mockito.anyString());
// JsonObject json = new JsonObject();
// json.addProperty("status", true);
// assertEquals(json, endpoint.request(method, url, data));
// }
//
// @Test
// public void testConvertToJson() throws Exception {
// AbstractEndpoint endpoint = Mockito.mock(AbstractEndpoint.class, Mockito.CALLS_REAL_METHODS);
// JsonObject json = new JsonObject();
// json.addProperty("status", true);
// assertEquals(json, endpoint.convertToJson("{\"status\":true}"));
// }
//
// }
