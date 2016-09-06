package com.veridu.idos;

import com.veridu.idos.endpoints.profiles.Digested;
import com.veridu.idos.endpoints.profiles.Normalised;
import com.veridu.idos.endpoints.profiles.ProfileAttributes;
import com.veridu.idos.endpoints.profiles.ProfileFeatures;
import com.veridu.idos.endpoints.profiles.ProfileFlags;
import com.veridu.idos.endpoints.profiles.ProfileGates;
import com.veridu.idos.endpoints.profiles.ProfileReferences;
import com.veridu.idos.endpoints.profiles.ProfileScores;
import com.veridu.idos.endpoints.profiles.ProfileSources;
import com.veridu.idos.endpoints.profiles.ProfileTasks;
import com.veridu.idos.endpoints.profiles.ProfileWarnings;
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.utils.IdOSUtils;

/**
 * CredentialFactory Class creates all Endpoints
 *
 */
public class CredentialFactory {

    /**
     * Necessary companyToken to make requests to the api
     */
    private String credentialToken;
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
     * Constructor Class
     * 
     * @param issuerPublicKey
     * @param issuerPrivateKey
     * @param subjectPublicKey
     */
    public CredentialFactory(String issuerPrivateKey, String issuerPublicKey, String subjectPublicKey) {
        this.credentialToken = IdOSUtils.generateToken(issuerPrivateKey, issuerPublicKey, subjectPublicKey);
    }

    public CredentialFactory(String token) {
        this.credentialToken = token;
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
}
