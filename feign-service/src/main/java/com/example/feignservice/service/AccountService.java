package com.example.feignservice.service;

import com.example.feignservice.client.AccountClient;
import com.example.feignservice.client.BudgetClient;
import com.example.feignservice.dto.AccountDTO;
import com.example.feignservice.dto.BudgetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountClient accountClient;

    @Autowired
    private BudgetClient budgetClient;

    @Autowired
    private AuthService authService;

    public AccountDTO create(AccountDTO accountDTO) {
        AccountDTO newAccount = accountClient.create(accountDTO);

        Long accountId = newAccount.getId();
        Long userId = authService.getCurrentUser().getId();
        Long budgetId = budgetClient.getBudgetByUser(userId.toString()).getId();

        BudgetDTO budget = budgetClient.getBudgetByUser(budgetId.toString());
        budget.getAccounts().add(accountId);
        budgetClient.update(budget);

        return newAccount;
    }

    public List<AccountDTO> getAccountsByBudget() {
        Long userId = authService.getCurrentUser().getId();
        Long budgetId = budgetClient.getBudgetByUser(userId.toString()).getId();

        return accountClient.getAccountsByBudget(budgetId.toString());
    }
}