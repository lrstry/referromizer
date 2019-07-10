package com.lrstry.referromizer.web;

import com.lrstry.referromizer.domain.Provider;
import com.lrstry.referromizer.domain.ProviderRequest;
import com.lrstry.referromizer.service.ProviderRequestService;
import com.lrstry.referromizer.service.ProviderService;
import com.lrstry.referromizer.util.Sanitizer;
import com.lrstry.referromizer.util.Validator;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackException;
import net.gpedro.integrations.slack.SlackMessage;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    @Autowired
    ProviderService providerService;
    @Autowired
    ProviderRequestService providerRequestService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Provider>> readAllProviders() {
        return new ResponseEntity<>(providerService.fetchAllProviders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<List<ProviderRequest>> readAllProviderRequests() {
        return new ResponseEntity<>(providerRequestService.fetchAllProviderRequests(), HttpStatus.OK);
    }

    @RequestMapping(value = "/requests", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    ResponseEntity<Object> createProviderRequest(@RequestBody ProviderRequest providerRequestBody) {

        Validate.notNull(providerRequestBody);

        ProviderRequest.ProviderRequestBuilder newProviderRequestBuilder;

        String providerName = providerRequestBody.getName();
        Validate.notEmpty(providerName);
        String exampleReferralUrl = providerRequestBody.getExampleReferralUrl();
        Validate.notEmpty(exampleReferralUrl);

        providerName = Sanitizer.sanitizeRequestedProviderName(providerName);
        exampleReferralUrl = Sanitizer.sanitizeUrl(exampleReferralUrl);

        if (!Validator.isValidProviderName(providerName)) {
            return new ResponseEntity<>("Requested Provider Name is invalid.", HttpStatus.BAD_REQUEST);
        }

        if (!Validator.isValidUrl(exampleReferralUrl)) {
            return new ResponseEntity<>("Example Referral Url for Requested Provider is invalid.", HttpStatus.BAD_REQUEST);
        }

        if (providerService.nameExists(providerName)) {
            return new ResponseEntity<>("Provider already existing.", HttpStatus.BAD_REQUEST);
        }

        if (providerRequestService.nameExists(providerName)) {
            return new ResponseEntity<>("Provider Request already existing.", HttpStatus.BAD_REQUEST);
        }

        newProviderRequestBuilder = ProviderRequest.builder(providerName, exampleReferralUrl);
        ProviderRequest newProviderRequest = providerRequestService.newProviderRequest(newProviderRequestBuilder);

        try {
            SlackApi slackApi = new SlackApi("https://hooks.slack.com/services/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");
            SlackMessage message = new SlackMessage();
            message.setUsername("ReferromizerBot");
            message.setText("A new provider request has been submitted: " + newProviderRequest.getName() + ", " + newProviderRequest.getExampleReferralUrl());
            message.setIcon(":ghost:");
            slackApi.call(message);
        } catch (SlackException e) {
            // Log
        }

        return new ResponseEntity<>(newProviderRequest, HttpStatus.OK);

    }

    @RequestMapping(value = "/requests/upvote", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    ResponseEntity<Object> upvoteProviderRequest(@RequestBody ProviderRequest providerRequestBody) {

        Validate.notNull(providerRequestBody.getId());
        ProviderRequest updatedProviderRequest = providerRequestService.upvoteProviderRequest(providerRequestBody.getId());

        return new ResponseEntity<>(updatedProviderRequest, HttpStatus.OK);

    }

}
