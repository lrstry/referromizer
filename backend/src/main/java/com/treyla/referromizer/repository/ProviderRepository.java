package com.treyla.referromizer.repository;

import com.treyla.referromizer.domain.Provider;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProviderRepository extends CrudRepository<Provider, UUID> {

    List<Provider> findAllByOrderByNameAsc();

    boolean existsByName(String name);

}
