package com.brooksource.saydemo;

import com.brooksource.saydemo.model.Customer;
import com.brooksource.saydemo.model.Transaction;
import com.brooksource.saydemo.repository.CustomerRepository;
import com.brooksource.saydemo.repository.TransactionRepository;
import com.brooksource.saydemo.util.DateStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class SayDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SayDemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner setUpRepository(CustomerRepository customerRepository,
											 TransactionRepository transactionRepository)
	{
		return (args) -> {
			Instant now = Instant.now();
			 customerRepository.saveAll(Arrays.asList(
				 new Customer("Basic Boundary Cases"),
				 new Customer("Basic Cumulative Cases"),
				 new Customer("Large Cumulative Transactions"),
				 new Customer("Multiple Transactions"))
			 );
			transactionRepository.saveAll(
					Arrays.asList(new Transaction(
							Timestamp.from(now.minus(61, ChronoUnit.DAYS)),
							1L,
							new BigDecimal("50.00")),
					new Transaction(
							Timestamp.from(now.minus(31, ChronoUnit.DAYS)),
							1L,
							new BigDecimal("100.00")),
					new Transaction(
							Timestamp.from(now.minus(2, ChronoUnit.DAYS)),
							1L,
							new BigDecimal("140.00")),
					new Transaction(
							Timestamp.from(now.minus(60, ChronoUnit.DAYS)),
							2L,
							new BigDecimal("55.01")),
					new Transaction(
							Timestamp.from(now.minus(31, ChronoUnit.DAYS)),
							2L,
							new BigDecimal("120.36")),
					new Transaction(
							Timestamp.from(now.minus(1, ChronoUnit.DAYS)),
							2L,
							new BigDecimal("80.36")),
					new Transaction(
							Timestamp.from(now.minus(60, ChronoUnit.DAYS)),
							3L,
							new BigDecimal("200.49")),
					new Transaction(
							Timestamp.from(now.minus(30, ChronoUnit.DAYS)),
							3L,
							new BigDecimal("250.86")),
					new Transaction(
							Timestamp.from(now.minus(1, ChronoUnit.DAYS)),
							3L,
							new BigDecimal("1030.49")),
					new Transaction(
							Timestamp.from(now.minus(61, ChronoUnit.DAYS)),
							4L,
							new BigDecimal("75.81")),
					new Transaction(
							Timestamp.from(now.minus(52, ChronoUnit.DAYS)),
							4L,
							new BigDecimal("65.21")),
					new Transaction(
							Timestamp.from(now.minus(31, ChronoUnit.DAYS)),
							4L,
							new BigDecimal("75.01")),
					new Transaction(
							Timestamp.from(now.minus(29, ChronoUnit.DAYS)),
							4L,
							new BigDecimal("175.51")),
					new Transaction(
							Timestamp.from(now.minus(2, ChronoUnit.DAYS)),
							4L,
							new BigDecimal("155.45")),
					new Transaction(
							Timestamp.from(now.minus(1, ChronoUnit.DAYS)),
							4L,
							new BigDecimal("159.58"))
					)
			);
		};
	}

}
