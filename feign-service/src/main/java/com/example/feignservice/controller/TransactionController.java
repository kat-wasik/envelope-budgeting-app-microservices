package com.example.feignservice.controller;

import com.example.feignservice.dto.TransactionDTO;
import com.example.feignservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("account")
    public List<TransactionDTO> getTransactionsByAccount(@RequestParam(name = "id") String accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }

    @GetMapping("{id}")
    public TransactionDTO getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);

    }

    @PostMapping
    public TransactionDTO create(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
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
