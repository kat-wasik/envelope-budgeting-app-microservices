package com.example.budgetservice.exception;

public class BudgetNotFoundException extends RuntimeException {
    public BudgetNotFoundException(String message) {
        super(message);
    }
}