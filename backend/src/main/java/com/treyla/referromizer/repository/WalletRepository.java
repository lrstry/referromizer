package com.lrstry.referromizer.repository;

import com.lrstry.referromizer.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WalletRepository extends CrudRepository<Wallet, UUID> {

}
