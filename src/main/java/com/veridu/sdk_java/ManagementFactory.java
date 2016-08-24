package com.veridu.sdk_java;

import com.veridu.sdk_java.endpoints.Credentials;
import com.veridu.sdk_java.endpoints.Members;
import com.veridu.sdk_java.endpoints.Settings;
import com.veridu.sdk_java.exceptions.InvalidToken;
import com.veridu.sdk_java.utils.Utils;

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
     * Instantiates Memebrs endpoint
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

}
