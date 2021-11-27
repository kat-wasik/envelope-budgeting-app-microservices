package com.example.feignservice.service;

import com.example.feignservice.client.AccountClient;
import com.example.feignservice.client.BudgetClient;
import com.example.feignservice.dto.AccountDTO;
import com.example.feignservice.dto.BudgetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountClient accountClient;

    @Autowired
    private BudgetClient budgetClient;

    public AccountDTO create(AccountDTO accountDTO) {
        AccountDTO newAccount = accountClient.create(accountDTO);

        Long accountId = newAccount.getId();
        Long budgetId = newAccount.getBudget();

        BudgetDTO budget = budgetClient.getBudget(budgetId);
        budget.getAccounts().add(accountId);
        budgetClient.update(budget);

        return newAccount;
    }
}