package com.lrstry.referromizer.service;

import com.lrstry.referromizer.domain.Wallet;
import com.lrstry.referromizer.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public List<Wallet> fetchAllWallets() {
        return (List<Wallet>) walletRepository.findAll();
    }

}
