package com.lrstry.referromizer.web;

import com.lrstry.referromizer.domain.Provider;
import com.lrstry.referromizer.domain.Referral;
import com.lrstry.referromizer.service.ProviderService;
import com.lrstry.referromizer.service.ReferralService;
import com.lrstry.referromizer.util.Sanitizer;
import com.lrstry.referromizer.util.Validator;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackException;
import net.gpedro.integrations.slack.SlackMessage;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

import static com.lrstry.referromizer.domain.Referral.ReferralBuilder;

@RestController
@RequestMapping("/api/referrals")
public class ReferralController {

    @Autowired
    ProviderService providerService;

    @Autowired
    ReferralService referralService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    ResponseEntity<Object> createReferral(@RequestBody Referral referralBody) {

        Validate.notNull(referralBody);

        ReferralBuilder newReferralBuilder;
        UUID providerId = referralBody.getProvider().getId();
        Provider provider = providerService.fetchProviderById(providerId);

        Validate.notNull(provider);

        String refUrl = referralBody.getRefUrl();
        Validate.notEmpty(refUrl);

        refUrl = Sanitizer.sanitizeUrl(refUrl);

        String refId = Referral.extractRefIdFromValidRefUrl(refUrl, provider);

        if (!Validator.isValidUrl(refUrl, provider.getRefUrlRegex())) {
            return new ResponseEntity<>("Referral Url is invalid.", HttpStatus.BAD_REQUEST);
        }

        if (referralService.refIdExists(refId)) {
            return new ResponseEntity<>("Referral already existing.", HttpStatus.BAD_REQUEST);
        }

        newReferralBuilder = Referral.builder(provider, refUrl);
        Referral newReferral = referralService.newReferral(newReferralBuilder);

        try {
            SlackApi slackApi = new SlackApi("https://hooks.slack.com/services/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");
            SlackMessage message = new SlackMessage();
            message.setUsername("ReferromizerBot");
            message.setText("A new referral has been submitted: "
                    + newReferral.getProvider().getName() + ", "
                    + newReferral.getRefId() + ", "
                    + newReferral.getRefUrl());
            message.setIcon(":ghost:");
            slackApi.call(message);
        } catch (SlackException e) {
            // Log
        }

        return new ResponseEntity<>(newReferral, HttpStatus.OK);

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
