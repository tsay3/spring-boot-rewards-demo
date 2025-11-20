package com.brooksource.saydemo.service;

import com.brooksource.saydemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brooksource.saydemo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository repo;

    public int getPointsForMonth(Long id, int month) {
        int total = 0;
        List<Transaction> list = repo.findByMonthValue(month);
        for (Transaction oneTransaction : list) {
//            Transaction oneTransaction = (Transaction) oneItem;
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
