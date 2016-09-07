package com.veridu.idos;

import java.util.HashMap;

import com.veridu.idos.endpoints.Companies;
import com.veridu.idos.endpoints.Credentials;
import com.veridu.idos.endpoints.Digested;
import com.veridu.idos.endpoints.Hooks;
import com.veridu.idos.endpoints.Members;
import com.veridu.idos.endpoints.Normalised;
import com.veridu.idos.endpoints.Permissions;
import com.veridu.idos.endpoints.ProfileAttributes;
import com.veridu.idos.endpoints.ProfileFeatures;
import com.veridu.idos.endpoints.ProfileFlags;
import com.veridu.idos.endpoints.ProfileGates;
import com.veridu.idos.endpoints.ProfileReferences;
import com.veridu.idos.endpoints.ProfileScores;
import com.veridu.idos.endpoints.ProfileSources;
import com.veridu.idos.endpoints.ProfileTags;
import com.veridu.idos.endpoints.ProfileTasks;
import com.veridu.idos.endpoints.ProfileWarnings;
import com.veridu.idos.endpoints.ServiceHandlers;
import com.veridu.idos.endpoints.Services;
import com.veridu.idos.endpoints.Settings;
import com.veridu.idos.endpoints.Token;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.utils.IdOSUtils;

/**
 * CredentialFactory Endpoint creates all Endpoints
 *
 */
public class Factory {

    /**
     * Company token
     */
    private String companyToken;

    /**
     * Credential token
     */
    private String credentialToken;

    /**
     * User Token
     */
    private String userToken;
    /**
     * Companies object
     */
    private Companies company;
    /**
     * Permissions object
     */
    private Permissions permission;

    /**
     * Credentials Endpoint object
     */
    private Credentials credential;

    /**
     * Settings Endpoint object
     */
    private Settings setting;

    /**
     * Members Endpoint object
     */
    private Members member;

    /**
     * Hooks Endpoint object
     */
    private Hooks hook;

    /**
     * ProfileTags Endpoint object
     */
    private ProfileTags tag;

    /**
     * Services Endpoint object
     */
    private Services service;

    /**
     * Service Handlers Endpoint object
     */
    private ServiceHandlers serviceHandler;

    /**
     * Mapped Endpoint object
     */
    private Normalised normalised;

    /**
     * ProfileFeatures Endpoint object
     */
    private ProfileFeatures feature;

    /**
     * ProfileFlags Endpoint object
     */
    private ProfileFlags flag;

    /**
     * ProfileGates Endpoint object
     */
    private ProfileGates gate;

    /**
     * ProfileReferences Endpoint object
     */
    private ProfileReferences reference;

    /**
     * ProfileSources Endpoint object
     */
    private ProfileSources source;

    /**
     * ProfileTasks Endpoint object
     */
    private ProfileTasks task;

    /**
     * ProfileAttributes Endpoint object
     */
    private ProfileAttributes attribute;

    /**
     * ProfileScores Endpoint object
     */
    private ProfileScores score;

    /**
     * Digested Endpoint object
     */
    private Digested digested;

    /**
     * ProfileWarnings Endpoint object
     */
    private ProfileWarnings warning;

    /**
     * Tokens Endpoint object
     */
    private Token token;

    /**
     * Class constructor
     * 
     * @param companyToken
     * @throws Emptytoken
     */
    // Refazer
    public Factory(String token) throws InvalidToken {
        if ((token == null) || token.isEmpty())
            throw new InvalidToken();

        this.setCompanyToken(token);
    }

    public Factory(HashMap<String, String> credentials) {
        if ((credentials.containsKey("companyPrivateKey")) && (credentials.containsKey("companyPublicKey"))) {
            if (credentials.containsKey("companySubject")) {
                this.companyToken = IdOSUtils.generateCompanyToken(credentials.get("companyPrivateKey"),
                        credentials.get("companyPublicKey"), credentials.get("companySubject"));
            } else {
                this.companyToken = IdOSUtils.generateCompanyToken(credentials.get("companyPrivateKey"),
                        credentials.get("companyPublicKey"));
            }
        }

        if ((credentials.containsKey("handlerPrivateKey")) && (credentials.containsKey("handlerPublicKey"))
                && (credentials.containsKey("credentialPublicKey"))) {
            this.credentialToken = IdOSUtils.generateCredentialToken(credentials.get("handlerPrivateKey"),
                    credentials.get("handlerPublicKey"), credentials.get("credentialPublicKey"));
        }

        if ((credentials.containsKey("credentialPrivateKey")) && (credentials.containsKey("credentialPublicKey"))
                && (credentials.containsKey("username"))) {
            this.userToken = IdOSUtils.generateUserToken(credentials.get("credentialPrivateKey"),
                    credentials.get("credentialPublicKey"), credentials.get("username"));
        }
    }

    public Factory(String privateKey, String publicKey, String subject) {
        this.setCompanyToken(IdOSUtils.generateCompanyToken(privateKey, publicKey, subject));
    }

    /**
     * Instantiates Companies endpoint
     * 
     * @return
     * 
     * @throws Emptytoken
     */
    public Companies getCompany() throws InvalidToken {
        if (!(this.company instanceof Companies))
            this.company = new Companies(this.companyToken);
        return this.company;
    }

    /**
     * Instantiates Permissions endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Permissions getPermission() throws InvalidToken {
        if (!(this.permission instanceof Permissions))
            this.permission = new Permissions(this.companyToken);
        return this.permission;
    }

    /**
     * Instantiates Credentials endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Credentials getCredential() throws InvalidToken {
        if (!(this.credential instanceof Credentials))
            this.credential = new Credentials(this.companyToken);
        return this.credential;
    }

    /**
     * Instantiates Settings endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Settings getSetting() throws InvalidToken {
        if (!(this.setting instanceof Settings))
            this.setting = new Settings(this.companyToken);
        return this.setting;
    }

    /**
     * Instantiates Members endpoint
     * 
     * @return
     * @throws InvalidToken
     */
    public Members getMember() throws InvalidToken {
        if (!(this.member instanceof Members))
            this.member = new Members(this.companyToken);
        return this.member;
    }

    /**
     * Instantiates Hooks endpoint
     * 
     * @return
     * @throws InvalidToken
     */

    public Hooks getHook() throws InvalidToken {
        if (!(this.hook instanceof Hooks))
            this.hook = new Hooks(this.companyToken);
        return this.hook;
    }

    /**
     * Instantiates Tag endpoint
     * 
     * @return ProfileTags instance
     * @throws InvalidToken
     */
    public ProfileTags getTag() throws InvalidToken {
        if (!(this.tag instanceof ProfileTags))
            this.tag = new ProfileTags(this.companyToken);
        return this.tag;
    }

    /**
     * Instantiates Services endpoint
     * 
     * @return Services instance
     * @throws InvalidToken
     */
    public Services getService() throws InvalidToken {
        if (!(this.service instanceof Services))
            this.service = new Services(this.companyToken);
        return this.service;
    }

    /**
     * Instantiates ServiceHandlers endpoint
     * 
     * @return ServiceHandlers instance
     * @throws InvalidToken
     */
    public ServiceHandlers getServiceHandler() throws InvalidToken {
        if (!(this.serviceHandler instanceof ServiceHandlers))
            this.serviceHandler = new ServiceHandlers(this.companyToken);
        return this.serviceHandler;
    }

    /**
     * Instantiates Attribute endpoint
     * 
     * @return ProfileAttribute instance
     * @throws InvalidToken
     */
    public ProfileAttributes getAttribute() throws InvalidToken {
        if (!(this.attribute instanceof ProfileAttributes))
            this.attribute = new ProfileAttributes(this.credentialToken);
        return this.attribute;
    }

    /**
     * Instantiates Normalised endpoint
     * 
     * @return Normalised instance
     * @throws InvalidToken
     */
    public Normalised getNormalised() throws InvalidToken {
        if (!(this.normalised instanceof Normalised))
            this.normalised = new Normalised(this.credentialToken);
        return this.normalised;
    }

    /**
     * Instantiates Feature endpoint
     * 
     * @return ProfileFeatures instance
     * @throws InvalidToken
     */
    public ProfileFeatures getFeature() throws InvalidToken {
        if (!(this.feature instanceof ProfileFeatures))
            this.feature = new ProfileFeatures(this.credentialToken);
        return this.feature;
    }

    /**
     * Instantiates Flag endpoint
     * 
     * @return ProfileFlags instance
     * @throws InvalidToken
     */
    public ProfileFlags getFlag() throws InvalidToken {
        if (!(this.flag instanceof ProfileFlags))
            this.flag = new ProfileFlags(this.credentialToken);
        return this.flag;
    }

    /**
     * Instantiates Gate endpoint
     * 
     * @return ProfileGates instance
     * @throws InvalidToken
     */
    public ProfileGates getGate() throws InvalidToken {
        if (!(this.gate instanceof ProfileGates))
            this.gate = new ProfileGates(this.credentialToken);
        return this.gate;
    }

    /**
     * Instantiates Reference endpoint
     * 
     * @return ProfileReferences instance
     * @throws InvalidToken
     */
    public ProfileReferences getReference() throws InvalidToken {
        if (!(this.reference instanceof ProfileReferences))
            this.reference = new ProfileReferences(this.credentialToken);
        return this.reference;
    }

    /**
     * Instantiates Task endpoint
     * 
     * @return ProfileTasks instance
     * @throws Invalidtoken
     */
    public ProfileTasks getTask() throws InvalidToken {
        if (!(this.task instanceof ProfileTasks))
            this.task = new ProfileTasks(this.credentialToken);
        return this.task;
    }

    /**
     * Instantiates Score endpoint
     * 
     * @return ProfileScores instance
     * @throws InvalidToken
     */
    public ProfileScores getScore() throws InvalidToken {
        if (!(this.score instanceof ProfileScores))
            this.score = new ProfileScores(this.credentialToken);
        return this.score;
    }

    /**
     * Instantiates Digested endpoint
     * 
     * @return Digested instance
     * @throws InvalidToken
     */
    public Digested getDigested() throws InvalidToken {
        if (!(this.digested instanceof Digested))
            this.digested = new Digested(this.credentialToken);
        return this.digested;
    }

    /**
     * Instantiates Warning endpoint
     * 
     * @return ProfileWarnings instance
     * @throws InvalidToken
     */
    public ProfileWarnings getWarning() throws InvalidToken {
        if (!(this.warning instanceof ProfileWarnings))
            this.warning = new ProfileWarnings(this.credentialToken);
        return this.warning;
    }

    /**
     * Instantiates Token endpoint
     * 
     * @return Token instance
     * @throws InvalidToken
     */
    public Token getToken() throws InvalidToken {
        if (!(this.token instanceof Token))
            this.token = new Token(this.userToken);
        return this.token;
    }

    public ProfileSources getSource() throws InvalidToken {
        if (!(this.source instanceof ProfileSources)) {
            this.source = new ProfileSources(this.userToken);
        }
        return this.source;
    }

    /**
     * Retrieves company token
     * 
     * @return companyToken
     */
    public String getCompanyToken() {
        return this.companyToken;
    }

    /**
     * Sets the company token necessary to make requests
     * 
     * @param companyToken
     */
    public void setCompanyToken(String companyToken) {
        this.companyToken = companyToken;
    }
}
