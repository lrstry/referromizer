package com.treyla.referromizer.service;

import com.treyla.referromizer.domain.Provider;
import com.treyla.referromizer.repository.ProviderRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public Provider fetchProviderById(UUID providerId) {
        Validate.notNull(providerId);
        return providerRepository.findOne(providerId);
    }

    @Override
    public List<Provider> fetchAllProviders() {
        return providerRepository.findAllByOrderByNameAsc();
    }

    @Override
    public boolean nameExists(String name) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        return providerRepository.existsByNameIgnoreCase(name);
    }

}
