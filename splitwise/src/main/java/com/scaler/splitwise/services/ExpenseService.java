package com.scaler.splitwise.services;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
