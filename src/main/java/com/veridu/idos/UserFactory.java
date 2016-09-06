package com.veridu.idos;

import com.veridu.idos.endpoints.users.Token;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.utils.IdOSUtils;

/**
 * UserFactory Class creates all Endpoints
 *
 */
public class UserFactory {
    /**
     * Necessary userToken to make requests to the api
     */
    private String userToken;
    /**
     * Tokens Endpoint object
     */
    private Token token;

    /**
     * Class constructor
     * 
     * @param userToken
     * @throws Emptytoken
     */
    public UserFactory(String token) throws InvalidToken {
        if ((token == null) || token.isEmpty())
            throw new InvalidToken();

        this.userToken = token;
    }

    /**
     * generates a user token
     * 
     * @param credentialPrivKey
     * @param credentialPubKey
     * @param username
     */
    public UserFactory(String credentialPrivKey, String credentialPubKey, String username) {
        this.userToken = IdOSUtils.generateUserToken(credentialPrivKey, credentialPubKey, username);
    }

    /**
     * Instantiates Token endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Token getToken() throws InvalidToken {
        if (!(this.token instanceof Token))
            this.token = new Token(this.userToken);
        return this.token;
    }
}
