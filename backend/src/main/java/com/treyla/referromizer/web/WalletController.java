package com.lrstry.referromizer.web;

import com.lrstry.referromizer.domain.Wallet;
import com.lrstry.referromizer.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<List<Wallet>> readAllWallets() {
        return new ResponseEntity<>(walletService.fetchAllWallets(), HttpStatus.OK);
    }

}
