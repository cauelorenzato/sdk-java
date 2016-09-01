package com.veridu.idos;

import com.veridu.idos.endpoints.companies.Companies;
import com.veridu.idos.endpoints.companies.Credentials;
import com.veridu.idos.endpoints.companies.Hooks;
import com.veridu.idos.endpoints.companies.Members;
import com.veridu.idos.endpoints.companies.Permissions;
import com.veridu.idos.endpoints.companies.ProfileTags;
import com.veridu.idos.endpoints.companies.Settings;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.utils.Utils;

/**
 * CredentialFactory Endpoint creates all Endpoints
 *
 */
public class CompanyFactory {

    public static String token;

    /**
     * Companies instance
     */
    public Companies company;
    /**
     * Permissions instance
     */
    public Permissions permission;

    /**
     * Credentials Endpoint instance
     */
    public Credentials credential;

    /**
     * Settings Endpoint instance
     */
    public Settings setting;

    /**
     * Members Endpoint instance
     */
    public Members member;

    /**
     * Hooks Endpoint instance
     */
    public Hooks hook;

    /**
     * ProfileTags Endpoint object
     */
    public ProfileTags tag;

    /**
     * Class constructor
     * 
     * @param token
     * @throws Emptytoken
     */

    public CompanyFactory(String token) throws InvalidToken {
        if (token == null || token.isEmpty()) {
            throw new InvalidToken();
        }

        CompanyFactory.token = token;
    }

    public CompanyFactory(String privateKey, String publicKey) {
        CompanyFactory.token = Utils.generateCompanyToken(privateKey, publicKey);
    }

    public CompanyFactory(String privateKey, String publicKey, String subject) {
        CompanyFactory.token = Utils.generateCompanyToken(privateKey, publicKey, subject);
    }

    /**
     * Instantiates Companies endpoint
     * 
     * @return
     * 
     * @throws Emptytoken
     */
    public Companies getCompany() throws InvalidToken {
        if (!(this.company instanceof Companies)) {
            this.company = new Companies();
        }
        return this.company;
    }

    /**
     * Instantiates Permissions endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Permissions getPermission() throws InvalidToken {
        if (!(this.permission instanceof Permissions)) {
            this.permission = new Permissions();
        }
        return this.permission;
    }

    /**
     * Instantiates Credentials endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Credentials getCredential() throws InvalidToken {
        if (!(this.credential instanceof Credentials)) {
            this.credential = new Credentials();
        }
        return this.credential;
    }

    /**
     * Instantiates Settings endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Settings getSetting() throws InvalidToken {
        if (!(this.setting instanceof Settings)) {
            this.setting = new Settings();
        }
        return this.setting;
    }

    /**
     * Instantiates Members endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Members getMember() throws InvalidToken {
        if (!(this.member instanceof Members)) {
            this.member = new Members();
        }
        return this.member;
    }

    /**
     * Instantiates Hooks endpoint
     * 
     * @return
     * @throws InvalidToken
     */

    public Hooks getHook() throws InvalidToken {
        if (!(this.hook instanceof Hooks)) {
            this.hook = new Hooks();
        }
        return this.hook;
    }

    /**
     * Instantiates Tag endpoint
     * 
     * @return ProfileTags instance
     * @throws InvalidToken
     */
    public ProfileTags getTag() throws InvalidToken {
        if (!(this.tag instanceof ProfileTags)) {
            this.tag = new ProfileTags();
        }
        return this.tag;
    }

}
