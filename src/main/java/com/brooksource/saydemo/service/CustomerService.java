package com.brooksource.saydemo.service;

import com.brooksource.saydemo.model.Customer;
import com.brooksource.saydemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repo;

    public String getCustomerName(Long id) {
        Customer found = repo.findById(id).orElse(null);
        if (found != null) {
            return found.getName();
        }
        return "not found";
    }
}
