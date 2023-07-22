package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.SettleGroupExpenseRequestDto;
import com.scaler.splitwise.dtos.SettleGroupExpenseResponseDto;
import com.scaler.splitwise.dtos.Status;
import com.scaler.splitwise.exceptions.InvalidGroupIdException;
import com.scaler.splitwise.models.Transaction;
import com.scaler.splitwise.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public SettleGroupExpenseResponseDto settleExpenses(SettleGroupExpenseRequestDto request) {
        List<Transaction> transactions;
        SettleGroupExpenseResponseDto response = new SettleGroupExpenseResponseDto();

        try {
            transactions = this.groupService.settleExpenses(request.getGroupId());
        } catch (InvalidGroupIdException exception)  {
            response.setStatus(Status.ERROR);
            return response;
        }

        response.setTransactions(transactions);

        return response;
    }

}
