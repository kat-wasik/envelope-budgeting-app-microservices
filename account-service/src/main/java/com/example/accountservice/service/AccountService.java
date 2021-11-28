package com.example.accountservice.service;

import com.example.accountservice.dto.AccountDTO;
import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.model.Account;
import com.example.accountservice.model.AccountType;
import com.example.accountservice.model.Money;
import com.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<AccountDTO> getAccountsByBudget(Long budgetId) {
        return accountRepository.findAllByBudget(budgetId)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public AccountDTO getAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not found"));

        return mapToDto(account);
    }

    @Transactional
    public AccountDTO save(AccountDTO accountDTO) {
        Account account = accountRepository.save(mapToAccount(accountDTO));

        return mapToDto(account);
    }

    @Transactional
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    private Account mapToAccount(AccountDTO accountDTO) {
        return Account.builder()
                .name(accountDTO.getName())
                .balance(new Money(new BigDecimal(accountDTO.getBalance())))
                .type(AccountType.valueOf(accountDTO.getType().toUpperCase()))
                .budget(accountDTO.getBudget())
                .build();
    }

    private AccountDTO mapToDto(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .name(account.getName())
                .balance(account.getBalance().getAmount().toString())
                .type(account.getType().toString())
                .budget(account.getBudget())
                .build();

    }
}