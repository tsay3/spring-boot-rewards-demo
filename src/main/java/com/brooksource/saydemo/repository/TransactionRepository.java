package com.brooksource.saydemo.repository;

import com.brooksource.saydemo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

//    @Query("SELECT id FROM transaction WHERE (" +
//            "MONTH(timestamp) + 12 * YEAR(timestamp)" +
//            ") = ?#{month}")
    List<Transaction> findByMonthValue(int month);
}
