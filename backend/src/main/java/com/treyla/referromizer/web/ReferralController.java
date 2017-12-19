package com.treyla.referromizer.web;

import com.treyla.referromizer.domain.Provider;
import com.treyla.referromizer.domain.Referral;
import com.treyla.referromizer.service.ProviderService;
import com.treyla.referromizer.service.ReferralService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

import static com.treyla.referromizer.domain.Referral.ReferralBuilder;

@RestController
@RequestMapping("/api/referrals")
public class ReferralController {

    @Autowired
    ProviderService providerService;

    @Autowired
    ReferralService referralService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    ResponseEntity<Referral> createReferral(@RequestBody Referral referralBody) {

        Validate.notNull(referralBody);

        ReferralBuilder newReferralBuilder;
        UUID providerId = referralBody.getProvider().getId();
        Provider provider = providerService.fetchProviderById(providerId);

        Validate.notNull(provider);

        String refId = referralBody.getRefId();
        String refUrl = referralBody.getRefUrl();

        Validate.notEmpty(refId);
        Validate.notEmpty(refUrl);

        if (referralService.refIdExists(refId) || referralService.refUrlExists(refUrl)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (Objects.nonNull(provider)) {
            newReferralBuilder = Referral.builder(provider)
                    .withReferralId(refId)
                    .withReferralUrl(refUrl);
            Referral newReferral = referralService.newReferral(newReferralBuilder);

            return new ResponseEntity<>(newReferral, HttpStatus.OK);


        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{providerId}", produces = "application/json; charset=utf-8")
    public ResponseEntity<Referral> readRandomReferralForProvider(@PathVariable String providerId) {

        Validate.notEmpty(providerId);

        Referral randomReferral = referralService.fetchRandomReferralForProviderId(UUID.fromString(providerId));

        if (Objects.nonNull(randomReferral)) {
            return new ResponseEntity<>(randomReferral, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/count",
            produces = "application/json; charset=utf-8")
    public ResponseEntity<Long> readTotalReferrals() {

        Long totalReferrals = referralService.fetchTotalReferrals();
        return new ResponseEntity<>(totalReferrals, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{providerId}/count",
            produces = "application/json; charset=utf-8")
    public ResponseEntity<Long> readTotalReferralsForProvider(@PathVariable String providerId) {

        Validate.notEmpty(providerId);
        Long totalReferrals = referralService.fetchTotalReferralsForProviderId(UUID.fromString(providerId));
        return new ResponseEntity<>(totalReferrals, HttpStatus.OK);

    }

}
