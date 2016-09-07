package com.veridu.idos;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.veridu.idos.endpoints.Companies;
import com.veridu.idos.endpoints.Permissions;
import com.veridu.idos.exceptions.InvalidToken;

public class CompanyFactoryTest {

    @Test(expected = InvalidToken.class)
    public void testCompanyFactoryStringThrowsException() throws InvalidToken {
        Factory factory = new Factory("");
    }

    @Test
    public void testCompanyFactoryToken() throws InvalidToken {
        Factory cf = new Factory(null);
        assertNull(cf.getCompanyToken());
        cf.setCompanyToken("companyToken");
        assertSame("companyToken", cf.getCompanyToken());
    }

    @Test
    public void testGetCompany() throws InvalidToken {
        Factory factory = new Factory("companyToken");
        assertNull(factory.getCompany());
        factory.getCompany();
        assertTrue(factory.getCompany() instanceof Companies);
    }

    @Test
    public void testGetPermission() throws InvalidToken {
        Factory factory = new Factory("companyToken");
        assertNull(factory.getPermission());
        factory.getPermission();
        assertTrue(factory.getPermission() instanceof Permissions);
    }

}
