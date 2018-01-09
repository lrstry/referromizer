package com.treyla.referromizer.service;

import com.treyla.referromizer.domain.ProviderRequest;

import java.util.List;

import static com.treyla.referromizer.domain.ProviderRequest.ProviderRequestBuilder;

public interface ProviderRequestService {

    List<ProviderRequest> fetchAllProviderRequests();

    ProviderRequest newProviderRequest(ProviderRequestBuilder builder);

}
