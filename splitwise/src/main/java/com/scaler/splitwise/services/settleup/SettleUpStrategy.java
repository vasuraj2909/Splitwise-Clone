package com.scaler.splitwise.services.settleup;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SettleUpStrategy {

    List<Transaction> settle(List<Expense> expenses);
}
