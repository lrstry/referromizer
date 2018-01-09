package com.treyla.referromizer.repository;

import com.treyla.referromizer.domain.ProviderRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProviderRequestRepository extends CrudRepository<ProviderRequest, UUID> {

}
