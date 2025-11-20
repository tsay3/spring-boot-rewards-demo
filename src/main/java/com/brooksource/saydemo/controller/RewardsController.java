package com.brooksource.saydemo.controller;

import com.brooksource.saydemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsController {

    @Autowired
    TransactionService service;

    @GetMapping("/points/{id}")
    public String getPointsById(@PathVariable Long id) {
        String returnHTML = "<h1>Customer " + id + "</h1><ul>";
        int totalPoints = 0;
        for (int i = 0; i < 3; i++) {
            int monthsPoints = service.getPointsForMonth(id, i);
            returnHTML += "<li>" + monthsPoints + " points</li>";
        }
        returnHTML += "<li><b>Total:" + totalPoints + " points</b></li></ul>";
        return returnHTML;
    }
}
