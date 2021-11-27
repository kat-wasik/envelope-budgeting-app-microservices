package com.example.budgetservice.controller;

import com.example.budgetservice.dto.BudgetDTO;
import com.example.budgetservice.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping("/user")
    public BudgetDTO getBudgetByUser(@RequestParam(name = "id") String user) {
        return budgetService.getBudgetByUser(Long.valueOf(user));
    }

    @PostMapping
    public BudgetDTO create(@RequestBody BudgetDTO budgetDTO) {
        return budgetService.save(budgetDTO);
    }

    @PutMapping
    public BudgetDTO update(@RequestBody BudgetDTO budgetDTO) {
        return budgetService.update(budgetDTO);
    }
}