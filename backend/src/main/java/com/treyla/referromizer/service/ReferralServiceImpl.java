package com.treyla.referromizer.service;

import com.treyla.referromizer.domain.Referral;
import com.treyla.referromizer.repository.ReferralRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.treyla.referromizer.domain.Referral.ReferralBuilder;

@Service
public class ReferralServiceImpl implements ReferralService {

    @Autowired
    ReferralRepository referralRepository;

    @Override
    public Referral newReferral(ReferralBuilder builder) {
        Validate.notNull(builder);
        return referralRepository.save(builder.build());
    }

    @Override
    public Referral fetchRandomReferralForProviderId(UUID providerId) {

        List<Referral> allReferrals = referralRepository.findAllByProviderId(providerId);

        if (allReferrals.size() > 0) {
            int randomElementIndex = ThreadLocalRandom.current().nextInt(allReferrals.size()) % allReferrals.size();
            Referral randomReferral = allReferrals.get(randomElementIndex);
            randomReferral.hit();
            return referralRepository.save(randomReferral);
        } else {
            return null;
        }

    }

    @Override
    public Long fetchTotalReferralsForProviderId(UUID providerId) {
        Validate.notNull(providerId);
        return referralRepository.countByProviderId(providerId);
    }

    @Override
    public boolean refIdExists(String refId) {
        Validate.notNull(refId);
        Validate.notEmpty(refId);
        return referralRepository.existsByRefId(refId);
    }

    @Override
    public boolean refUrlExists(String refUrl) {
        Validate.notNull(refUrl);
        Validate.notEmpty(refUrl);
        return referralRepository.existsByRefUrl(refUrl);
    }

}
