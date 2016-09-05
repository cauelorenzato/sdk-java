package com.veridu.idos;

import com.veridu.idos.endpoints.users.Token;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.utils.Utils;

/**
 * UserFactory Class creates all Endpoints
 *
 */
public class UserFactory {
    /**
     * Necessary userToken to make requests to the api
     */
    public static String userToken;
    /**
     * Tokens Endpoint object
     */
    public Token token;

    /**
     * Class constructor
     * 
     * @param userToken
     * @throws Emptytoken
     */
    public UserFactory(String token) throws InvalidToken {
        if (token == null || token.isEmpty()) {
            throw new InvalidToken();
        }

        UserFactory.userToken = token;
    }

    /**
     * generates a user token
     * 
     * @param credentialPrivKey
     * @param credentialPubKey
     * @param username
     */
    public UserFactory(String credentialPrivKey, String credentialPubKey, String username) {
        UserFactory.userToken = Utils.generateUserToken(credentialPrivKey, credentialPubKey, username);
    }

    /**
     * Instantiates Token endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Token getToken() throws InvalidToken {
        if (!(this.token instanceof Token)) {
            this.token = new Token();
        }
        return this.token;
    }
}
