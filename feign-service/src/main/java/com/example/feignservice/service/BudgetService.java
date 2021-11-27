package com.example.feignservice.service;

import com.example.feignservice.client.BudgetClient;
import com.example.feignservice.dto.BudgetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    @Autowired
    private BudgetClient budgetClient;

    public BudgetDTO getBudget(Long id) {
        return budgetClient.getBudget(id);
    }

    public BudgetDTO create(BudgetDTO budgetDTO) {
        return budgetClient.create(budgetDTO);
    }
}
