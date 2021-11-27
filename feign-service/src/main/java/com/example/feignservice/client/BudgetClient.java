package com.example.feignservice.client;

import com.example.feignservice.dto.BudgetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient("budget-service")
public interface BudgetClient {
    @GetMapping("/api/budget/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BudgetDTO getBudget(@PathVariable Long id);

    @PostMapping("/api/budget")
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetDTO create(@RequestBody BudgetDTO budgetDTO);

    @PutMapping("/api/budget")
    public BudgetDTO update(@RequestBody BudgetDTO budgetDTO);
}