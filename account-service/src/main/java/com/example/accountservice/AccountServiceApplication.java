package com.example.accountservice;

import com.example.accountservice.dto.AccountDTO;
import com.example.accountservice.model.Account;
import com.example.accountservice.model.AccountType;
import com.example.accountservice.model.Money;
import com.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class AccountServiceApplication {
	@Autowired
	private AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	InitializingBean createBudgets() {
		return () -> {
			accountRepository.save(Account
					.builder()
					.name("mbank")
					.balance(new Money(new BigDecimal("1000")))
					.type(AccountType.valueOf("CHECKING"))
					.budget(Long.valueOf("1"))
					.build()
			);

			accountRepository.save(Account
					.builder()
					.name("santander")
					.balance(new Money(new BigDecimal("1000")))
					.type(AccountType.valueOf("CHECKING"))
					.budget(Long.valueOf("2"))
					.build()
			);
		};
	}
}
