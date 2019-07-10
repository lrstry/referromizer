package com.lrstry.referromizer.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "provider_request")
public class ProviderRequest {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String exampleReferralUrl;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private ProviderRequestStatus status;
    private int votes;

    ProviderRequest() {
    }

    public ProviderRequest(ProviderRequestBuilder builder) {
        this.name = builder.name;
        this.exampleReferralUrl = builder.exampleReferralUrl;
        this.status = ProviderRequestStatus.REQUESTED;
        this.votes = Math.incrementExact(this.votes);
    }

    public static ProviderRequestBuilder builder(String name, String exampleReferralUrl) {
        return new ProviderRequestBuilder(name, exampleReferralUrl);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExampleReferralUrl() {
        return exampleReferralUrl;
    }

    public int getVotes() {
        return votes;
    }

    public String getStatus() {
        switch (this.status) {
            case REQUESTED:
                return "Requested";
            case ACTIVE:
                return "Active";
            case DECLINED:
                return "Declined";
        }
        return null;
    }

    public void upvote() {
        this.votes = Math.incrementExact(this.votes);
    }

    public static final class ProviderRequestBuilder {

        // required parameter
        private String name;
        private String exampleReferralUrl;

        public ProviderRequestBuilder(String name, String exampleReferralUrl) {
            this.name = name;
            this.exampleReferralUrl = exampleReferralUrl;
        }

        public ProviderRequest build() {
            return new ProviderRequest(this);
        }

    }

}
