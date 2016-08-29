package com.veridu.idos;

import com.veridu.idos.endpoints.Credentials;
import com.veridu.idos.endpoints.Hooks;
import com.veridu.idos.endpoints.Members;
import com.veridu.idos.endpoints.Settings;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.utils.Utils;

/**
 * Factory Endpoint creates all Endpoints
 *
 */
public class ManagementFactory {

    public static String token;

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
     * Management constructor class
     * 
     * @param token
     * @throws InvalidToken
     */
    public ManagementFactory(String token) throws InvalidToken {
        if (token == null || token.isEmpty()) {
            throw new InvalidToken();
        }
        ManagementFactory.token = token;

    }

    public ManagementFactory(String issuerPublicKey, String issuerPrivateKey, String subjectPublicKey) {
        ManagementFactory.token = Utils.generateToken(issuerPublicKey, issuerPrivateKey, subjectPublicKey);

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
     */

    public Hooks getHook() throws InvalidToken {
        if (!(this.hook instanceof Hooks)) {
            this.hook = new Hooks();
        }
        return this.hook;
    }
}
