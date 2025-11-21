package com.brooksource.saydemo.controller;

import com.brooksource.saydemo.service.CustomerService;
import com.brooksource.saydemo.service.TransactionService;
import com.brooksource.saydemo.util.DateStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class RewardsController {

//    @Autowired
//    CustomerService customerService;
    @Autowired
    TransactionService transactionService;

    @GetMapping("/points/")
    public String getAllPoints() {
        return transactionService.getPointsResponseForAllUsers();
    }

    @GetMapping("/points/{id}")
    public String getPointsById(@PathVariable Long id) {
        if (id == null) return getAllPoints();
        return transactionService.getPointsResponseForUser(id);
    }
}
