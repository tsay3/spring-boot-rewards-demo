//package com.brooksource.sayDemo.repository;
//
//import com.brooksource.sayDemo.model.Customer;
//import com.brooksource.sayDemo.model.Transaction;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.time.Instant;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class CustomerRepositoryTest {
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    private Customer john;
//
//    @BeforeEach
//    public void setUp() {
//        john = new Customer("John");
//        customerRepository.save(john);
//    }
//
//    @Test
//    public void whenFindByName_thenReturnCustomer() {
////        john = new Customer("John");
//
//        Customer found = customerRepository.findById(john.getId()).orElse(null);
//        assertThat(found.getName()).isEqualTo(john.getName());
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//        customerRepository.delete(john);
//    }
//}
