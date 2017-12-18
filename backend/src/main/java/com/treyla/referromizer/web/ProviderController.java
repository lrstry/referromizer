package com.treyla.referromizer.web;

import com.treyla.referromizer.domain.Provider;
import com.treyla.referromizer.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Provider>> readAllProviders() {
        return new ResponseEntity<>(providerService.fetchAllProviders(), HttpStatus.OK);
    }

}
