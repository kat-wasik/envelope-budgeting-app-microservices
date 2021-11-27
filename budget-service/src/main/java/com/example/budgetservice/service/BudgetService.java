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
    public BudgetDTO getBudget(Long id) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException("Budget with id " + id + " not found"));
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
                .accounts(budgetDTO.getAccounts())
                .build();
    }

    private BudgetDTO mapToDto(Budget budget) {
        return BudgetDTO.builder()
                .id(budget.getId())
                .user(budget.getUser())
                .accounts(budget.getAccounts())
                .build();
    }


}
