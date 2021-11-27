package com.example.feignservice.controller;

import com.example.feignservice.dto.BudgetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.feignservice.service.BudgetService;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BudgetDTO getBudget(@PathVariable Long id) {
        return budgetService.getBudget(id);
    };

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetDTO create(@RequestBody BudgetDTO budgetDTO) {
        return budgetService.create(budgetDTO);
    }
}