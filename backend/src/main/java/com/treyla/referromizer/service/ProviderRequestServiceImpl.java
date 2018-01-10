package com.treyla.referromizer.service;

import com.treyla.referromizer.domain.ProviderRequest;
import com.treyla.referromizer.repository.ProviderRequestRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.treyla.referromizer.domain.ProviderRequest.ProviderRequestBuilder;

@Service
public class ProviderRequestServiceImpl implements ProviderRequestService {

    @Autowired
    ProviderRequestRepository providerRequestRepository;

    @Override
    public List<ProviderRequest> fetchAllProviderRequests() {
        return providerRequestRepository.findAllByOrderByVotesDesc();
    }

    @Override
    public ProviderRequest newProviderRequest(ProviderRequestBuilder builder) {
        Validate.notNull(builder);
        return providerRequestRepository.save(builder.build());
    }

    @Override
    public ProviderRequest upvoteProviderRequest(UUID id) {
        Validate.notNull(id);
        ProviderRequest providerRequest = providerRequestRepository.findById(id);
        Validate.notNull(providerRequest);
        providerRequest.upvote();
        return providerRequestRepository.save(providerRequest);
    }

    @Override
    public ProviderRequest fetchProviderRequestById(UUID id) {
        Validate.notNull(id);
        return providerRequestRepository.findById(id);
    }

    @Override
    public boolean nameExists(String name) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        return providerRequestRepository.existsByName(name);
    }
}
