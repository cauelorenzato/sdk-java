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
import com.veridu.idos.exceptions.InvalidToken;
import com.veridu.idos.utils.Utils;

/**
 * CredentialFactory Class creates all Endpoints
 *
 */
public class CredentialFactory {

    /**
     * Necessary companyToken to make requests to the api
     */
    public static String credentialToken;
    /**
     * Mapped Endpoint object
     */
    public Normalised normalised;

    /**
     * ProfileFeatures Endpoint object
     */
    public ProfileFeatures feature;

    /**
     * ProfileFlags Endpoint object
     */
    public ProfileFlags flag;

    /**
     * ProfileGates Endpoint object
     */
    public ProfileGates gate;

    /**
     * ProfileReferences Endpoint object
     */
    public ProfileReferences reference;

    /**
     * ProfileSources Endpoint object
     */
    public ProfileSources source;

    /**
     * ProfileTasks Endpoint object
     */
    public ProfileTasks task;

    /**
     * ProfileAttributes Endpoint object
     */
    public ProfileAttributes attribute;

    /**
     * ProfileScores Endpoint object
     */
    public ProfileScores score;

    /**
     * Digested Endpoint object
     */
    public Digested digested;

    /**
     * Constructor Class
     * 
     * @param issuerPublicKey
     * @param issuerPrivateKey
     * @param subjectPublicKey
     */
    public CredentialFactory(String issuerPrivateKey, String issuerPublicKey, String subjectPublicKey) {
        CredentialFactory.credentialToken = Utils.generateToken(issuerPrivateKey, issuerPublicKey, subjectPublicKey);
    }

    public CredentialFactory(String token) {
        CredentialFactory.credentialToken = token;
    }

    /**
     * Instantiates Attribute endpoint
     * 
     * @return ProfileAttribute instance
     * @throws InvalidToken
     */
    public ProfileAttributes getAttribute() throws InvalidToken {
        if (!(this.attribute instanceof ProfileAttributes)) {
            this.attribute = new ProfileAttributes();
        }
        return this.attribute;
    }

    /**
     * Instantiates Normalised endpoint
     * 
     * @return Normalised instance
     * @throws InvalidToken
     */
    public Normalised getNormalized() throws InvalidToken {
        if (!(this.normalised instanceof Normalised)) {
            this.normalised = new Normalised();
        }
        return this.normalised;
    }

    /**
     * Instantiates Feature endpoint
     * 
     * @return ProfileFeatures instance
     * @throws InvalidToken
     */
    public ProfileFeatures getFeature() throws InvalidToken {
        if (!(this.feature instanceof ProfileFeatures)) {
            this.feature = new ProfileFeatures();
        }
        return this.feature;
    }

    /**
     * Instantiates Flag endpoint
     * 
     * @return ProfileFlags instance
     * @throws InvalidToken
     */
    public ProfileFlags getFlag() throws InvalidToken {
        if (!(this.flag instanceof ProfileFlags)) {
            this.flag = new ProfileFlags();
        }
        return this.flag;
    }

    /**
     * Instantiates Gate endpoint
     * 
     * @return ProfileGates instance
     * @throws InvalidToken
     */
    public ProfileGates getGate() throws InvalidToken {
        if (!(this.gate instanceof ProfileGates)) {
            this.gate = new ProfileGates();
        }
        return this.gate;
    }

    /**
     * Instantiates Reference endpoint
     * 
     * @return ProfileReferences instance
     * @throws InvalidToken
     */
    public ProfileReferences getReference() throws InvalidToken {
        if (!(this.reference instanceof ProfileReferences)) {
            this.reference = new ProfileReferences();
        }
        return this.reference;
    }

    /**
     * Instantiates Task endpoint
     * 
     * @return ProfileTasks instance
     * @throws Invalidtoken
     */
    public ProfileTasks getTask() throws InvalidToken {
        if (!(this.task instanceof ProfileTasks)) {
            this.task = new ProfileTasks();
        }
        return this.task;
    }

    /**
     * Instantiates Score endpoint
     * 
     * @return ProfileScores instance
     * @throws InvalidToken
     */
    public ProfileScores getScore() throws InvalidToken {
        if (!(this.score instanceof ProfileScores)) {
            this.score = new ProfileScores();
        }
        return this.score;
    }

    /**
     * Instantiates Digested endpoint
     * 
     * @return Digested instance
     * @throws InvalidToken
     */
    public Digested getDigested() throws InvalidToken {
        if (!(this.digested instanceof Digested)) {
            this.digested = new Digested();
        }
        return this.digested;
    }
}
