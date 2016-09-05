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
        assertNull(CompanyFactory.companyToken);
        CompanyFactory factory = new CompanyFactory("companyToken");
        assertSame("companyToken", CompanyFactory.companyToken);
    }

    @Test
    public void testGetCompany() throws InvalidToken {
        CompanyFactory factory = new CompanyFactory("companyToken");
        assertNull(factory.company);
        factory.getCompany();
        assertTrue(factory.company instanceof Companies);
    }

    @Test
    public void testGetPermission() throws InvalidToken {
        CompanyFactory factory = new CompanyFactory("companyToken");
        assertNull(factory.permission);
        factory.getPermission();
        assertTrue(factory.permission instanceof Permissions);
    }

}
