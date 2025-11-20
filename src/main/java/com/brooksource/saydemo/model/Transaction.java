package com.brooksource.saydemo.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;

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

    public Long getId() {
        return id;
    }
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

//    public void setTimestamp(Timestamp timestamp) {
//        this.timestamp = timestamp;
//    }

    public Long getCustomerId() {
        return customerId;
    }

//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }

    public BigDecimal getAmount() {
        return amount;
    }

//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
}
