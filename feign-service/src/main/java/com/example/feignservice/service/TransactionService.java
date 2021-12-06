package com.example.feignservice.service;

import com.example.feignservice.client.TransactionClient;
import com.example.feignservice.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionClient transactionClient;

    @Autowired
    private AccountService accountService;

    public List<TransactionDTO> getTransactionsByAccount(String accountId) {
        return transactionClient.getTransactionsByAccount(accountId);
    }

    public TransactionDTO getTransaction(Long id) {
        return transactionClient.getTransaction(id);
    }

    public TransactionDTO create(TransactionDTO transactionDTO) {
        Long accountId = transactionDTO.getAccount();
        TransactionDTO newTransaction =  transactionClient.create(transactionDTO);
        accountService.updateBalance(accountId);
        return newTransaction;
    }

    public void delete(Long id) {
        Long accountId = transactionClient.getTransaction(id).getAccount();
        TransactionDTO transactionDTO = transactionClient.getTransaction(id);
        String transactionAmount = transactionDTO.getAmount();
        transactionClient.delete(id);
        accountService.updateBalance(accountId);
    }

    public TransactionDTO update(TransactionDTO transactionDTO) {
        return transactionClient.update(transactionDTO);
    }
}
