package com.example.budgetservice.controller;

import com.example.budgetservice.dto.BudgetDTO;
import com.example.budgetservice.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BudgetDTO getBudget(@PathVariable Long id) {
        return budgetService.getBudget(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetDTO create(@RequestBody BudgetDTO budgetDTO) {
        return budgetService.save(budgetDTO);
    }

    @PutMapping
    public BudgetDTO update(@RequestBody BudgetDTO budgetDTO) {
        return budgetService.update(budgetDTO);
    }
}