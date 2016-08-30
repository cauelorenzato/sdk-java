package com.veridu.idos;

import com.veridu.idos.endpoints.companies.Companies;
import com.veridu.idos.endpoints.companies.Permissions;
import com.veridu.idos.exceptions.EmptyPrivateKey;

/**
 * Factory Endpoint creates all Endpoints
 *
 */
public class CompanyFactory {

    public static String privateKey;

    /**
     * Companies instance
     */
    public Companies company;
    /**
     * Permissions instance
     */
    public Permissions permission;

    /**
     * Class constructor
     * 
     * @param privateKey
     * @throws EmptyPrivateKey
     */

    public CompanyFactory(String privateKey) throws EmptyPrivateKey {
        if (privateKey == null || privateKey.isEmpty()) {
            throw new EmptyPrivateKey();
        }

        CompanyFactory.privateKey = privateKey;
    }

    /**
     * Instantiates Companies endpoint
     * 
     * @return
     * 
     * @throws EmptyPrivateKey
     */
    public Companies getCompany() throws EmptyPrivateKey {
        if (!(this.company instanceof Companies)) {
            this.company = new Companies();
        }
        return this.company;
    }

    public Permissions getPermission() throws EmptyPrivateKey {
        if (!(this.permission instanceof Permissions)) {
            this.permission = new Permissions();
        }
        return this.permission;
    }

}
