package com.scaler.splitwise.services.settleup;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BruteForceSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settle(List<Expense> expenses) {


        return null;
    }
}