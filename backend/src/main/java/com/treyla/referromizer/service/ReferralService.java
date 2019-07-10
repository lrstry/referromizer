package com.lrstry.referromizer.service;

import com.lrstry.referromizer.domain.Referral;

import java.util.UUID;

import static com.lrstry.referromizer.domain.Referral.ReferralBuilder;

public interface ReferralService {

    Referral newReferral(ReferralBuilder referral);

    Referral fetchRandomReferralForProviderId(UUID providerId);

    Long fetchTotalReferrals();

    Long fetchTotalReferralsForProviderId(UUID providerId);

    boolean refIdExists(String refId);

    boolean refUrlExists(String refUrl);

}
