package com.veridu.sdk_java.endpoints;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.veridu.sdk_java.CompanyFactory;
import com.veridu.sdk_java.endpoints.AbstractEndpoint;
import com.veridu.sdk_java.exceptions.EmptyPrivateKey;
import com.veridu.sdk_java.exceptions.SDKException;

public class AbstractEndpointTest {
	
	private AbstractEndpoint abstractMock = null;

	@Before
	public void setUp() throws Exception {
		this.abstractMock = spy(AbstractEndpoint.class);
	}

	@After
	public void tearDown() throws Exception {
		this.abstractMock = null;
	}
	
	@Test
	public void testFetch() throws SDKException {
		when(this.abstractMock.transformURL("POST", "/companies", "name=Company")).thenReturn("http://localhost:8080/index.php/1.0/companies&companyPrivKey=privKey");
		JsonObject json = new JsonObject();
		json.addProperty("dummy", "value");;
		when(this.abstractMock.request("POST", "http://localhost:8080/index.php/1.0/companies&companyPrivKey=privKey", "name=Company")).thenReturn(json);
		assertTrue(this.abstractMock.fetch("POST", "/companies", "name=Company") instanceof JsonObject);
		assertSame(json, this.abstractMock.fetch("POST", "/companies", "name=Company"));
	}

	@Test
	public void testTransformURLGetMethod() throws EmptyPrivateKey {
		assertEquals("http://localhost:8080/index.php/1.0/companies?companyPrivKey=null", this.abstractMock.transformURL("GET", "companies", ""));
		CompanyFactory factory = new CompanyFactory("privKey");
		assertEquals("http://localhost:8080/index.php/1.0/companies?companyPrivKey=privKey", this.abstractMock.transformURL("GET", "companies", ""));		
	}

	@Test
	public void testQueryBuilderHashMapOfStringString() throws UnsupportedEncodingException {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("key1", "value 1");
		data.put("key2", "value 2");
		data.put("key3", "value 3");
		assertEquals("key1=value+1&key2=value+2&key3=value+3",this.abstractMock.queryBuilder(data));
		
	}

	@Test
	public void testQueryBuilderStringString() throws UnsupportedEncodingException {
		assertEquals("key=new+value", this.abstractMock.queryBuilder("key", "new value"));
	}

	@Test
	public void testRequest() throws IOException {
		JsonObject json = new JsonObject();
		doNothing().when(this.abstractMock).setConnectionSettings("http://localhost:8080/index.php/1.0/companies?companyPrivKey=privKey", "GET");
		doNothing().when(this.abstractMock).sendRequest("GET", "");
		when(this.abstractMock.getResponse()).thenReturn(json);
		assertEquals(json, this.abstractMock.request("GET", "http://localhost:8080/index.php/1.0/companies?companyPrivKey=privKey", ""));
	}

	@Test
	public void testSetConnectionSettings() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testConvertToJson() {
		fail("Not yet implemented");
	}

}
