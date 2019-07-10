package com.lrstry.referromizer.service;

import com.lrstry.referromizer.domain.Provider;

import java.util.List;
import java.util.UUID;

public interface ProviderService {

    Provider fetchProviderById(UUID providerId);

    List<Provider> fetchAllProviders();

    boolean nameExists(String name);

}
