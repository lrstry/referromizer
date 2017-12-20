package com.treyla.referromizer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "referral")
public class Referral {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "provider_id")
    @JsonBackReference
    private Provider provider;
    private String refId;
    private String refUrl;
    private int hits;

    Referral() {
    }

    public Referral(ReferralBuilder builder) {
        this.provider = builder.provider;
        this.refId = builder.refId;
        this.refUrl = sanitizeUrl(builder.refUrl);
    }

    public static ReferralBuilder builder(Provider provider) {
        return new ReferralBuilder(provider);
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    private void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getRefId() {
        return refId;
    }

    private void setRefId(String refId) {
        this.refId = refId;
    }

    public String getRefUrl() {
        return refUrl;
    }

    private void setRefUrl(String refUrl) {
        this.refUrl = refUrl;
    }

    public int getHits() {
        return hits;
    }

    public void hit() {
        this.hits = Math.incrementExact(this.hits);
    }

    public static final class ReferralBuilder {

        // required parameter
        private Provider provider;

        // optional parameters
        private String refId;
        private String refUrl;

        public ReferralBuilder(Provider provider) {
            this.provider = provider;
        }

        public ReferralBuilder withReferralId(String refId) {
            this.refId = refId;
            return this;
        }

        public ReferralBuilder withReferralUrl(String refUrl) {
            this.refUrl = refUrl;
            return this;
        }

        public Referral build() {
            return new Referral(this);
        }

    }

    public static String sanitizeUrl(String url) {
        if (!url.matches("^(http|https)://.*$")){
            return "http://" + url;
        } else {
            return url;
        }
    }

}
