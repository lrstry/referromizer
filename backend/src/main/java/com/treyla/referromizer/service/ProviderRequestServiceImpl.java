package com.treyla.referromizer.service;

import com.treyla.referromizer.domain.ProviderRequest;
import com.treyla.referromizer.repository.ProviderRequestRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.treyla.referromizer.domain.ProviderRequest.ProviderRequestBuilder;

@Service
public class ProviderRequestServiceImpl implements ProviderRequestService {

    @Autowired
    ProviderRequestRepository providerRequestRepository;

    @Override
    public List<ProviderRequest> fetchAllProviderRequests() {
        return (List<ProviderRequest>) providerRequestRepository.findAll();
    }

    @Override
    public ProviderRequest newProviderRequest(ProviderRequestBuilder builder) {
        Validate.notNull(builder);
        return providerRequestRepository.save(builder.build());
    }
}
