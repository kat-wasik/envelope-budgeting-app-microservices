package com.example.budgetservice.service;

import com.example.budgetservice.dto.BudgetDTO;
import com.example.budgetservice.exception.BudgetNotFoundException;
import com.example.budgetservice.model.Budget;
import com.example.budgetservice.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Transactional(readOnly = true)
    public BudgetDTO getBudgetByUser(Long user) {
        Budget budget = budgetRepository.findByUser(user)
                .orElseThrow(() -> new BudgetNotFoundException("Budget of user with id " + user + " not found"));
        return mapToDto(budget);
    }

    @Transactional
    public BudgetDTO save(BudgetDTO budgetDTO) {
        Budget budget = budgetRepository.save(mapToBudget(budgetDTO));
        return mapToDto(budget);
    }

    @Transactional
    public BudgetDTO update(BudgetDTO budgetDTO) {
        Long id = budgetDTO.getId();
        Budget updatedBudget = mapToBudget(budgetDTO);

        if (budgetRepository.findById(id).isPresent()) {
            updatedBudget.setId(id);
            budgetRepository.save(updatedBudget);
        } else {
            budgetRepository.save(updatedBudget);
        }

        return mapToDto(updatedBudget);
    }

    private Budget mapToBudget(BudgetDTO budgetDTO) {
        return Budget.builder()
                .user(budgetDTO.getUser())
                .build();
    }

    private BudgetDTO mapToDto(Budget budget) {
        return BudgetDTO.builder()
                .id(budget.getId())
                .user(budget.getUser())
                .build();
    }


}
