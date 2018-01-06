package com.treyla.referromizer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
import java.util.regex.Matcher;

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
        this.refUrl = builder.refUrl;
        this.refId = extractRefIdFromValidRefUrl(this.refUrl);
    }

    public static ReferralBuilder builder(Provider provider, String refUrl) {
        return new ReferralBuilder(provider, refUrl);
    }

    public UUID getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getRefId() {
        return refId;
    }

    public String getRefUrl() {
        return refUrl;
    }

    public int getHits() {
        return hits;
    }

    public void hit() {
        this.hits = Math.incrementExact(this.hits);
    }

    private String extractRefIdFromValidRefUrl(String refUrl) {
        Matcher refUrlMatcher = provider.getRefUrlMatcher(refUrl);
        if (refUrlMatcher.matches()) {
            return refUrlMatcher.group(1);
        }
        return null;
    }

    public static final class ReferralBuilder {

        // required parameter
        private Provider provider;
        private String refUrl;

        public ReferralBuilder(Provider provider, String refUrl) {
            this.provider = provider;
            this.refUrl = refUrl;
        }

        public Referral build() {
            return new Referral(this);
        }

    }

}
