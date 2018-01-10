package com.treyla.referromizer.service;

import com.treyla.referromizer.domain.ProviderRequest;

import java.util.List;
import java.util.UUID;

import static com.treyla.referromizer.domain.ProviderRequest.ProviderRequestBuilder;

public interface ProviderRequestService {

    List<ProviderRequest> fetchAllProviderRequests();

    ProviderRequest fetchProviderRequestById(UUID id);

    ProviderRequest newProviderRequest(ProviderRequestBuilder builder);

    ProviderRequest upvoteProviderRequest(UUID id);
}
