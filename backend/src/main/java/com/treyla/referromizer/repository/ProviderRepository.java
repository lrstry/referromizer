package com.treyla.referromizer.repository;

import com.treyla.referromizer.domain.Provider;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProviderRepository extends CrudRepository<Provider, UUID> {

    boolean existsByName(String name);

}
