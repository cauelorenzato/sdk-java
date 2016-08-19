package com.veridu.sdk_java;

import static org.junit.Assert.*;

import org.junit.Test;

import com.veridu.sdk_java.endpoints.Companies;
import com.veridu.sdk_java.exceptions.EmptyPrivateKey;

public class CompanyFactoryTest {

	@Test (expected=EmptyPrivateKey.class)
	public void testCompanyFactoryStringThrowsException() throws EmptyPrivateKey {
		CompanyFactory factory = new CompanyFactory("");
	}
	
	@Test
	public void testCompanyFactoryString() throws EmptyPrivateKey {
		assertNull(CompanyFactory.privateKey);
		CompanyFactory factory = new CompanyFactory("privateKey");
		assertSame("privateKey", CompanyFactory.privateKey);
	}
	
	@Test
	public void testGetCompany() throws EmptyPrivateKey {
		CompanyFactory factory = new CompanyFactory("privKey");
		assertNull(factory.company);
		factory.getCompany();
		assertTrue(factory.company instanceof Companies);
	}

}
