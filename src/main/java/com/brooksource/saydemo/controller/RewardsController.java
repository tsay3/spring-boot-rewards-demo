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

    @Autowired
    CustomerService customerService;
    @Autowired
    TransactionService transactionService;

    @GetMapping("/points/{id}")
    public String getPointsById(@PathVariable Long id) {
        StringBuilder returnHTML = new StringBuilder("<h1>Customer ");
        returnHTML.append(customerService.getCustomerName(id));
        returnHTML.append("</h1>");

        int totalPoints = 0;
        int currentMonth = DateStorage.timestampToMonth(Instant.now());

        for (int i = currentMonth - 2; i <= currentMonth; i++) {
            int monthsPoints = transactionService.getPointsForMonth(id, i);
            returnHTML.append("<p>");
            returnHTML.append(DateStorage.monthValueToString(i));
            returnHTML.append(": ");
            returnHTML.append(monthsPoints);
            returnHTML.append(" points</p>");
            totalPoints += monthsPoints;
        }
        returnHTML.append("<p><b>Total: ");
        returnHTML.append(totalPoints);
        returnHTML.append(" points</b></p>");
        returnHTML.append("<i>Generated on ");
        returnHTML.append(DateStorage.monthValueToString(
                DateStorage.timestampToMonth(Instant.now())));
        returnHTML.append("</i>");

        return returnHTML.toString();
    }
}
