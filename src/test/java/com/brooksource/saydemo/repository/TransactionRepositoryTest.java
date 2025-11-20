package com.brooksource.saydemo.repository;

import com.brooksource.saydemo.model.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@EntityScan(basePackages = "com.brooksource.sayDemo")
@ContextConfiguration(classes = {TransactionRepository.class})
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;
//    @Autowired
//    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

//    private Customer john;

    @BeforeEach
    public void setUp() {
//        john = new Customer("John");
//        customerRepository.save(john);
//        entityManager.persist(john);
//        entityManager.flush();
    }

    @AfterEach
//    public void tearDown() {
//        customerRepository.delete(john);
//    }

    @Test
    public void whenFindById_thenReturnTransaction() {

        Transaction j1 = new Transaction(Timestamp.from(Instant.now().minus(20, ChronoUnit.MINUTES)),
                1L, new BigDecimal("80.00"));

        entityManager.persistAndFlush(j1);

        Transaction found = (Transaction) transactionRepository.findById(j1.getId()).orElse(null);
        assertThat(found != null);
        assertThat(found.getAmount()).isEqualTo(j1.getAmount());
    }
}