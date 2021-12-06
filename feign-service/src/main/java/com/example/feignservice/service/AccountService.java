package com.example.feignservice.service;

import com.example.feignservice.client.AccountClient;
import com.example.feignservice.client.BudgetClient;
import com.example.feignservice.client.TransactionClient;
import com.example.feignservice.dto.AccountDTO;
import com.example.feignservice.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountClient accountClient;

    @Autowired
    private BudgetClient budgetClient;

    @Autowired
    private TransactionClient transactionClient;

    @Autowired
    private AuthService authService;

    public AccountDTO create(AccountDTO accountDTO) {
        Long userId = authService.getCurrentUser().getId();
        Long budgetId = budgetClient.getBudgetByUser(userId.toString()).getId();
        accountDTO.setBudget(budgetId);
        AccountDTO newAccount = accountClient.create(accountDTO);

        TransactionDTO firstTransaction = TransactionDTO.builder()
                .account(newAccount.getId())
                .amount(newAccount.getBalance())
                .currency(newAccount.getCurrency())
                .date(LocalDate.now().toString())
                .description("starting balance")
                .build();

        transactionClient.create(firstTransaction);

        return newAccount;
    }

    public List<AccountDTO> getAccountsByBudget() {
        Long userId = authService.getCurrentUser().getId();
        Long budgetId = budgetClient.getBudgetByUser(userId.toString()).getId();

        return accountClient.getAccountsByBudget(budgetId.toString());
    }

    public void delete(Long id) {
        accountClient.delete(id);
        transactionClient.deleteTransactionsByAccount(id.toString());
    }

    public AccountDTO getAccount(Long id) {
        return accountClient.getAccount(id);
    }

    public AccountDTO update(AccountDTO accountDTO) {
        return accountClient.update(accountDTO);
    }

    public void updateBalance(Long accountId) {
        AccountDTO accountDTO = accountClient.getAccount(accountId);
        System.out.println("old balance: " + accountDTO.getBalance());
        String newBalance = transactionClient.getBalanceByAccount(accountId.toString());
        System.out.println("new balance: " + newBalance);
        accountDTO.setBalance(newBalance);
        accountClient.update(accountDTO);
    }
}
