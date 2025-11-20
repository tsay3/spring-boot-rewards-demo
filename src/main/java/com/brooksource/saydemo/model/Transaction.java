package com.brooksource.saydemo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.brooksource.saydemo.util.DateStorage;
import jakarta.persistence.*;

@Entity
@Table(name="transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp timestamp;
    private Long customerId;
    private BigDecimal amount;
    private int monthValue;

    public Transaction() {}

    public Transaction(Timestamp timestamp,
                       Long customerId, BigDecimal amount) {
        this.timestamp = timestamp;
        this.customerId = customerId;
        this.amount = amount;
        this.monthValue = DateStorage.timestampToMonth(timestamp);
    }

    public Long getCustomerId() {
        return customerId;
    }


    public BigDecimal getAmount() {
        return amount;
    }
}
