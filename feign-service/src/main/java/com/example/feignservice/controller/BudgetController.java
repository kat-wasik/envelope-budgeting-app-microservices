package com.example.feignservice.controller;

import com.example.feignservice.dto.BudgetDTO;
import com.example.feignservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.feignservice.service.BudgetService;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public BudgetDTO getBudgetByUser() {
        return budgetService.getBudgetByUser();
    }

    @PostMapping
    public BudgetDTO create(@RequestBody BudgetDTO budgetDTO) {
        return budgetService.create(budgetDTO);
    }
}