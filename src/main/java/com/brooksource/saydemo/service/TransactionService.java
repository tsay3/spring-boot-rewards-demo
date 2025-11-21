package com.brooksource.saydemo.service;

import com.brooksource.saydemo.model.Customer;
import com.brooksource.saydemo.repository.CustomerRepository;
import com.brooksource.saydemo.repository.TransactionRepository;
import com.brooksource.saydemo.util.DateStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brooksource.saydemo.model.Transaction;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    TransactionRepository repo;

    public String getPointsResponseForAllUsers() {
        StringBuilder returnHTML = new StringBuilder("<h1>All Customers</h1>");
//        returnHTML.append("TO DO: add an endpoint to show ALL customers' points");

        int currentMonth = DateStorage.timestampToMonth(Instant.now());
        // populate the points map
        List<Map<Long, Integer>> allCustomerPoints = new ArrayList<>();
        Map<Long, Integer> totalPoints = new HashMap<>();
        for (int i = currentMonth - 2; i <= currentMonth; i++) {
            Map<Long, Integer> customerPoints = new HashMap<>();
            List<Transaction> allTransactions = repo.findByMonthValue(i);
            for (Transaction t : allTransactions) {
                Long id = t.getCustomerId();
                int points = getPointsFromAmount(t.getAmount());
                customerPoints.put(id, customerPoints.getOrDefault(id, 0) + points);
                totalPoints.put(id, totalPoints.getOrDefault(id, 0) + points);
            }
            allCustomerPoints.add(customerPoints);
        }

        // create a user table
        List<Customer> allCustomers = customerRepo.findAll();
        List<Long> allIds = new ArrayList<>();
        returnHTML.append("<table><tr><th></th>");
        for (Customer customer : allCustomers) {
            allIds.add(customer.getId());
            returnHTML.append("<th>");
            returnHTML.append(customer.getId());
            returnHTML.append("</th>");
        }
        returnHTML.append("</tr><tr>");
        for (int i = 0; i <= 2; i++) {
            returnHTML.append("<tr>");
            int month = currentMonth - 2 + i;
            returnHTML.append("<th>");
            returnHTML.append(DateStorage.monthValueToString(month));
            returnHTML.append("</th>");
            for (Long id : allIds) {
                returnHTML.append("<td>");
                returnHTML.append(allCustomerPoints.get(i).get(id));
                returnHTML.append("</td>");
            }
            returnHTML.append("</tr>");
        }
        returnHTML.append("<tr><th>Totals</th>");
        for (Long id : allIds) {
            returnHTML.append("<td>");
            returnHTML.append(totalPoints.get(id));
            returnHTML.append("</td>");
        }
        returnHTML.append("</tr></table>");
        returnHTML.append(DateStorage.generationMessage());
        return returnHTML.toString();
    }

    public String getPointsResponseForUser(Long id) {
        StringBuilder returnHTML = new StringBuilder("<h1>Customer ");
        returnHTML.append(customerService.getCustomerName(id));
        returnHTML.append("</h1>");

        int totalPoints = 0;
        int currentMonth = DateStorage.timestampToMonth(Instant.now());

        for (int i = currentMonth - 2; i <= currentMonth; i++) {
            int monthsPoints = getPointsForUserAndMonth(id, i);
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
        returnHTML.append(DateStorage.generationMessage());

        return returnHTML.toString();
    }

    private int getPointsForUserAndMonth(Long id, int month) {
        int total = 0;
        List<Transaction> list = repo.findByMonthValue(month);
        for (Transaction oneTransaction : list) {
            if (oneTransaction.getCustomerId().equals(id)) {
                total += getPointsFromAmount(oneTransaction.getAmount());
            }
        }
        return total;
    }

    private int getPointsFromAmount(BigDecimal amount) {
        int totalPoints = 0;
        int roundedDown = amount.intValue();
        if (roundedDown > 100) {
            totalPoints = 2 * (roundedDown - 100) + 50;
        } else if (roundedDown > 50) {
            totalPoints = roundedDown - 50;
        }
        return totalPoints;
    }
}
