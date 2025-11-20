package com.brooksource.saydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository<Transaction> extends CrudRepository<Transaction, Long> {

    @Query("SELECT * FROM Transaction WHERE (" +
            "MONTH(TODAY()) - MONTH(timestamp)" +
            "+ 12 * (YEAR(TODAY() - YEAR(timestamp))" +
            ") = :#{month}")
    public List<Transaction> findByMonth(int month);
}
