package com.veridu.idos;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.veridu.idos.endpoints.companies.Companies;
import com.veridu.idos.endpoints.companies.Permissions;
import com.veridu.idos.exceptions.InvalidToken;

public class CompanyFactoryTest {

    @Test(expected = InvalidToken.class)
    public void testCompanyFactoryStringThrowsException() throws InvalidToken {
        CompanyFactory factory = new CompanyFactory("");
    }

    @Test
    public void testCompanyFactoryToken() throws InvalidToken {
        assertNull(CompanyFactory.token);
        CompanyFactory factory = new CompanyFactory("token");
        assertSame("token", CompanyFactory.token);
    }

    @Test
    public void testGetCompany() throws InvalidToken {
        CompanyFactory factory = new CompanyFactory("token");
        assertNull(factory.company);
        factory.getCompany();
        assertTrue(factory.company instanceof Companies);
    }

    @Test
    public void testGetPermission() throws InvalidToken {
        CompanyFactory factory = new CompanyFactory("token");
        assertNull(factory.permission);
        factory.getPermission();
        assertTrue(factory.permission instanceof Permissions);
    }

}
