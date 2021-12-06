package com.example.feignservice.client;

import com.example.feignservice.dto.AccountDTO;
import com.example.feignservice.dto.BudgetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("account-service")
public interface AccountClient {
    @GetMapping("api/account/budget")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAccountsByBudget(@RequestParam(name = "id") String budgetId);

    @GetMapping("api/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    AccountDTO getAccount(@PathVariable Long id);

    @PostMapping("api/account")
    @ResponseStatus(HttpStatus.CREATED)
    AccountDTO create(@RequestBody AccountDTO accountDTO);

    @PutMapping("api/account")
    AccountDTO update(@RequestBody AccountDTO accountDTO);

    @DeleteMapping("api/account/{id}")
    void delete(@PathVariable Long id);
}