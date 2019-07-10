package com.lrstry.referromizer.repository;

import com.lrstry.referromizer.domain.Referral;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
public interface ReferralRepository extends CrudRepository<Referral, UUID> {

    List<Referral> findAllByProviderId(UUID providerId);

    Long countByProviderId(UUID providerId);

    boolean existsByRefId(String refId);

    boolean existsByRefUrl(String refUrl);

}
