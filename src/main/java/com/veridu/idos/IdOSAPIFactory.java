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
import com.veridu.idos.endpoints.ProfileGates;
import com.veridu.idos.endpoints.ProfileRaw;
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

/**
 * CredentialFactory Endpoint creates all Endpoints
 *
 */
public class IdOSAPIFactory {

    /**
     * Credentials data
     */
    private HashMap<String, String> credentials;
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
     * Raw Endpoint object
     */
    private ProfileRaw raw;

    /**
     * Class constructor
     * 
     * @param credentials
     *            HashMap<String, String>
     */

    public IdOSAPIFactory(HashMap<String, String> credentials) {
        this.credentials = credentials;
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
            this.company = new Companies(this.credentials);
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
            this.permission = new Permissions(this.credentials);
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
            this.credential = new Credentials(this.credentials);
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
            this.setting = new Settings(this.credentials);
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
            this.member = new Members(this.credentials);
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
            this.hook = new Hooks(this.credentials);
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
            this.tag = new ProfileTags(this.credentials);
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
            this.service = new Services(this.credentials);
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
            this.serviceHandler = new ServiceHandlers(this.credentials);
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
            this.attribute = new ProfileAttributes(this.credentials);
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
            this.normalised = new Normalised(this.credentials);
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
            this.feature = new ProfileFeatures(this.credentials);
        return this.feature;
    }

    /**
     * Instantiates Gate endpoint
     * 
     * @return ProfileGates instance
     * @throws InvalidToken
     */
    public ProfileGates getGate() throws InvalidToken {
        if (!(this.gate instanceof ProfileGates))
            this.gate = new ProfileGates(this.credentials);
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
            this.reference = new ProfileReferences(this.credentials);
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
            this.task = new ProfileTasks(this.credentials);
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
            this.score = new ProfileScores(this.credentials);
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
            this.digested = new Digested(this.credentials);
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
            this.warning = new ProfileWarnings(this.credentials);
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
            this.token = new Token(this.credentials);
        return this.token;
    }

    /**
     * Instantiates Source endpoint
     * 
     * @return Source instance
     * @throws InvalidToken
     */
    public ProfileSources getSource() throws InvalidToken {
        if (!(this.source instanceof ProfileSources)) {
            this.source = new ProfileSources(this.credentials);
        }
        return this.source;
    }

    public ProfileRaw getRaw() throws InvalidToken {
        if (!(this.raw instanceof ProfileRaw)) {
            this.raw = new ProfileRaw(this.credentials);
        }
        return this.raw;
    }

}
