package com.scaler.splitwise.services;

import com.scaler.splitwise.exceptions.InvalidGroupIdException;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.models.Transaction;
import com.scaler.splitwise.repositories.GroupRepository;
import com.scaler.splitwise.services.settleup.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    public GroupService(GroupRepository groupRepository, @Qualifier("greedysettleupstrategy") SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Transaction> settleExpenses(Long groupId) throws InvalidGroupIdException {
        Optional<Group> group = groupRepository.findById(groupId);

        if (group.isEmpty()) {
            throw new InvalidGroupIdException();
        }

        List<Expense> expenses = group.get().getExpenses();

        List<Transaction> transactions = this.settleUpStrategy.settle(expenses);

        return transactions;
    }
}
