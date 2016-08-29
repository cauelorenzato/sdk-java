package com.veridu.idos;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.veridu.idos.CompanyFactory;
import com.veridu.idos.endpoints.Companies;
import com.veridu.idos.endpoints.Permissions;
import com.veridu.idos.exceptions.EmptyPrivateKey;

public class CompanyFactoryTest {

    @Test(expected = EmptyPrivateKey.class)
    public void testCompanyFactoryStringThrowsException() throws EmptyPrivateKey {
        CompanyFactory factory = new CompanyFactory("");
    }

    @Test
    public void testCompanyFactoryPrivateKey() throws EmptyPrivateKey {
        assertNull(CompanyFactory.privateKey);
        CompanyFactory factory = new CompanyFactory("privateKey");
        assertSame("privateKey", CompanyFactory.privateKey);
    }

    @Test
    public void testGetCompany() throws EmptyPrivateKey {
        CompanyFactory factory = new CompanyFactory("privateKey");
        assertNull(factory.company);
        factory.getCompany();
        assertTrue(factory.company instanceof Companies);
    }

    @Test
    public void testGetPermission() throws EmptyPrivateKey {
        CompanyFactory factory = new CompanyFactory("privateKey");
        assertNull(factory.permission);
        factory.getPermission();
        assertTrue(factory.permission instanceof Permissions);
    }

}
