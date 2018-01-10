package com.treyla.referromizer.repository;

import com.treyla.referromizer.domain.ProviderRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProviderRequestRepository extends CrudRepository<ProviderRequest, UUID> {

    List<ProviderRequest> findAllByOrderByVotesDesc();

    ProviderRequest findById(UUID id);

    boolean existsByName(String name);

}
