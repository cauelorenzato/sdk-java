package com.veridu.idos;

import com.veridu.idos.endpoints.Features;
import com.veridu.idos.endpoints.Mapped;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.utils.Utils;

/**
 * Factory Endpoint creates all Endpoints
 *
 */
public class Factory {

    /**
     * Necessary token to make requests to the api
     */
    public static String token;
    /**
     * Mapped Endpoint instance
     */
    public Mapped mapped;

    /**
     * Features Endpoint instance
     */
    public Features feature;

    /**
     * Constructor Class
     * 
     * @param issuerPublicKey
     * @param issuerPrivateKey
     * @param subjectPublicKey
     */
    public Factory(String issuerPublicKey, String issuerPrivateKey, String subjectPublicKey) {
        Factory.token = Utils.generateToken(issuerPublicKey, issuerPrivateKey, subjectPublicKey);
    }

    public Factory(String token) {
        Factory.token = token;
    }

    /**
     * Instantiates Mapped endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Mapped getMapped() throws InvalidToken {
        if (!(this.mapped instanceof Mapped)) {
            this.mapped = new Mapped();
        }
        return this.mapped;
    }

    public Features getFeature() throws InvalidToken {
        if (!(this.feature instanceof Features)) {
            this.feature = new Features();
        }
        return this.feature;
    }
}
