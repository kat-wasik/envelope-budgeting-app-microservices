package com.example.transactionservice.controller;

import com.example.transactionservice.dto.TransactionDTO;
import com.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/account")
    public List<TransactionDTO> getTransactionsByAccount(@RequestParam(name = "id") String accountId) {
        return transactionService.getTransactionsByAccount(Long.valueOf(accountId));
    }

    @DeleteMapping("/account")
    public void deleteTransactionsByAccount(@RequestParam(name = "id") String accountId) {
        transactionService.deleteTransactionsByAccount(Long.valueOf(accountId));
    }

    @GetMapping("/balance")
    public String getBalanceByAccount(@RequestParam(name = "accountId") String accountId) {
        return transactionService.calculateBalanceByAccount(Long.valueOf(accountId));
    }

    @GetMapping("{id}")
    public TransactionDTO getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @PostMapping
    public TransactionDTO create(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.save(transactionDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }

    @PutMapping
    public TransactionDTO update(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.update(transactionDTO);
    }
}
