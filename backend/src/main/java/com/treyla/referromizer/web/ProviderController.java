package com.treyla.referromizer.web;

import com.treyla.referromizer.domain.Provider;
import com.treyla.referromizer.domain.ProviderRequest;
import com.treyla.referromizer.service.ProviderRequestService;
import com.treyla.referromizer.service.ProviderService;
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

        if (providerRequestService.nameExists(providerName)) {
            return new ResponseEntity<>("Provider Request already existing.", HttpStatus.BAD_REQUEST);
        }

        newProviderRequestBuilder = ProviderRequest.builder(providerName, exampleReferralUrl);
        ProviderRequest newProviderRequest = providerRequestService.newProviderRequest(newProviderRequestBuilder);

        return new ResponseEntity<>(newProviderRequest, HttpStatus.OK);

    }

    @RequestMapping(value = "/requests/upvote", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    ResponseEntity<Object> upvoteProviderRequest(@RequestBody ProviderRequest providerRequestBody) {

        Validate.notNull(providerRequestBody.getId());
        ProviderRequest updatedProviderRequest = providerRequestService.upvoteProviderRequest(providerRequestBody.getId());

        return new ResponseEntity<>(updatedProviderRequest, HttpStatus.OK);

    }

}
