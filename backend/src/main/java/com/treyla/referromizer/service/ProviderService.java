package com.treyla.referromizer.service;

import com.treyla.referromizer.domain.Provider;

import java.util.List;
import java.util.UUID;

public interface ProviderService {

    Provider fetchProviderById(UUID providerId);
    List<Provider> fetchAllProviders();

}
