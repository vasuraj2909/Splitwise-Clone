package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.CreateExpenseRequestDto;
import com.scaler.splitwise.dtos.CreateExpenseResponseDto;
import com.scaler.splitwise.dtos.Status;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.repositories.UserRepository;
import com.scaler.splitwise.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class ExpenseController {
    private UserRepository userRepository;
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(UserRepository userRepository, ExpenseService expenseService) {
        this.userRepository = userRepository;
        this.expenseService = expenseService;
    }

    public CreateExpenseResponseDto create(CreateExpenseRequestDto request) {
        // 1. Change longs (ids) - > User objects
        CreateExpenseResponseDto responseDto = new CreateExpenseResponseDto();

        Map<User, Long> owedUserAmounts = new HashMap<>();
        for (Map.Entry<Long, Long> id_amount : request.getOwedBy().entrySet()) { // id:amount
            Long id = id_amount.getKey();
            Long amount = id_amount.getValue();

            Optional<User> user = userRepository.findById(id);

            if (user.isEmpty()) {
                responseDto.setStatus(Status.ERROR);
                return responseDto;
            }

            owedUserAmounts.put(user.get(), amount);
        }

        Map<User, Long> paidUserAmounts = new HashMap<>();
        for (Map.Entry<Long, Long> id_amount : request.getPaidBy().entrySet()) { // id:amount
            Long id = id_amount.getKey();
            Long amount = id_amount.getValue();

            Optional<User> user = userRepository.findById(id);

            if (user.isEmpty()) {
                responseDto.setStatus(Status.ERROR);
                return responseDto;
            }

            paidUserAmounts.put(user.get(), amount);
        }

        Optional<User> createdByUser = userRepository.findById(request.getUserId());

        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setCreatedBy(createdByUser.get());
        expense.setDescription(request.getDescription());
        expense.setCurrency(request.getCurrency());
        expense.setOwedBy(owedUserAmounts);
        expense.setPaidBy(paidUserAmounts);

        expenseService.createExpense(expense);

        responseDto.setExpense(expense);
        return responseDto;
    }
}

// create api to create a group expense