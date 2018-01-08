package com.treyla.referromizer.repository;

import com.treyla.referromizer.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WalletRepository extends CrudRepository<Wallet, UUID> {

}
