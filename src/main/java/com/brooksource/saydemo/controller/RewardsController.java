package com.brooksource.saydemo.controller;

import com.brooksource.saydemo.service.TransactionService;
import com.brooksource.saydemo.util.DateStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class RewardsController {

    @Autowired
    TransactionService service;

    @GetMapping("/points/{id}")
    public String getPointsById(@PathVariable Long id) {
        StringBuilder returnHTML = new StringBuilder("<h1>Customer " + id + "</h1>");
        int totalPoints = 0;
        int currentMonth = DateStorage.timestampToMonth(Instant.now());
        for (int i = currentMonth - 2; i <= currentMonth; i++) {
            int monthsPoints = service.getPointsForMonth(id, i);
            returnHTML.append("<p>" + DateStorage.monthValueToString(i));
            returnHTML.append(": " + monthsPoints + " points</p>");
            totalPoints += monthsPoints;
        }
        returnHTML.append("<p><b>Total: " + totalPoints + " points</b></p>");
        returnHTML.append("<i>Generated on " +
                DateStorage.monthValueToString(DateStorage.timestampToMonth(Instant.now())) +
                "</i>");
        return returnHTML.toString();
    }
}
