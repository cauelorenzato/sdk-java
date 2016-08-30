package com.veridu.idos;

import com.veridu.idos.endpoints.profiles.ProfileFlags;
import com.veridu.idos.endpoints.profiles.ProfileGates;
import com.veridu.idos.endpoints.profiles.ProfileReferences;
import com.veridu.idos.endpoints.profiles.ProfileSources;
import com.veridu.idos.endpoints.profiles.ProfileTags;
import com.veridu.idos.endpoints.profiles.Profiles;
import com.veridu.idos.endpoints.profiles.sources.Normalized;
import com.veridu.idos.endpoints.profiles.sources.ProfileFeatures;
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
     * Mapped Endpoint object
     */
    public Normalized normalized;

    /**
     * Profiles Endpoint object
     */
    public Profiles profile;

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
     * ProfileTags Endpoint object
     */
    public ProfileTags tag;

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
     * Instantiates Profile endpoint
     * 
     * @return Profile instance
     * @throws InvalidToken
     */
    public Profiles getProfile() throws InvalidToken {
        if (!(this.profile instanceof Profiles)) {
            this.profile = new Profiles();
        }
        return this.profile;
    }

    /**
     * Instantiates Normalized endpoint
     * 
     * @return Nornalized instance
     * @throws InvalidToken
     */
    public Normalized getNormalized() throws InvalidToken {
        if (!(this.normalized instanceof Normalized)) {
            this.normalized = new Normalized();
        }
        return this.normalized;
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
