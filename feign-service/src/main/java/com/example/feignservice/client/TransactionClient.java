package com.example.feignservice.client;

import com.example.feignservice.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("transaction-service")
public interface TransactionClient {
    @GetMapping("/api/transaction/account")
    public List<TransactionDTO> getTransactionsByAccount(@RequestParam(name = "id") String accountId);

    @GetMapping("/api/transaction/{id}")
    public TransactionDTO getTransaction(@PathVariable Long id);

    @PostMapping("/api/transaction")
    public TransactionDTO create(@RequestBody TransactionDTO transactionDTO);

    @DeleteMapping("/api/transaction/{id}")
    public void delete(@PathVariable Long id);
}
