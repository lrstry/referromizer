package com.lrstry.referromizer.service;

import com.lrstry.referromizer.domain.ProviderRequest;

import java.util.List;
import java.util.UUID;

import static com.lrstry.referromizer.domain.ProviderRequest.ProviderRequestBuilder;

public interface ProviderRequestService {

    List<ProviderRequest> fetchAllProviderRequests();

    ProviderRequest fetchProviderRequestById(UUID id);

    ProviderRequest newProviderRequest(ProviderRequestBuilder builder);

    ProviderRequest upvoteProviderRequest(UUID id);

    boolean nameExists(String name);

}
