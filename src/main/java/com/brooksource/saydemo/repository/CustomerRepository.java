package com.brooksource.saydemo.repository;

import com.brooksource.saydemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    Customer findById(long id);
}
