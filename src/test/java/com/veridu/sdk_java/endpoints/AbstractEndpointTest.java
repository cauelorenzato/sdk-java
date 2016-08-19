package com.veridu.sdk_java.endpoints;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.veridu.sdk_java.exceptions.SDKException;
import com.veridu.sdk_java.settings.Settings;

import static org.mockito.Mockito.*;

public class AbstractEndpointTest {
	
	protected AbstractEndpoint abstractMock; 

	@Before
	public void setUp() throws Exception {
		this.abstractMock = spy(AbstractEndpoint.class);
	}

	@After
	public void tearDown() throws Exception {
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
	public void testTransformURLGetMethod() {
		assertSame("http://localhost:8080/index.php/1.0/companies&companyPrivKey=null", this.abstractMock.transformURL("GET", "companies", ""));
	
	}

	@Test
	public void testQueryBuilderHashMapOfStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryBuilderStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testRequest() {
		fail("Not yet implemented");
	}

}
