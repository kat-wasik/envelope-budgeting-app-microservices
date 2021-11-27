package com.example.feignservice.service;

import com.example.feignservice.client.BudgetClient;
import com.example.feignservice.dto.BudgetDTO;
import com.example.feignservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    @Autowired
    private BudgetClient budgetClient;

    @Autowired
    private AuthService authService;

    public BudgetDTO getBudgetByUser() {
        Long userId = authService.getCurrentUser().getId();

        return budgetClient.getBudgetByUser(userId.toString());
    }

    public BudgetDTO create(BudgetDTO budgetDTO) {
        return budgetClient.create(budgetDTO);
    }
}
