package com.scaler.splitwise.services.settleup;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Transaction;
import com.scaler.splitwise.models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("greedysettleupstrategy")
public class GreedySettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settle(List<Expense> expenses) {
        Map<Long, Long> user_owed_amount = new HashMap<>();

        for (Expense expense: expenses) {
            for (User user : expense.getOwedBy().keySet()) {
                if (!user_owed_amount.containsKey(user.getId())) {
                    user_owed_amount.put(user.getId(), 0L);
                }

                user_owed_amount.put(user.getId(), user_owed_amount.get(user.getId()) +
                        expense.getOwedBy().get(user));
            }

            for (User user : expense.getPaidBy().keySet()) {
                if (!user_owed_amount.containsKey(user.getId())) {
                    user_owed_amount.put(user.getId(), 0L);
                }

                user_owed_amount.put(user.getId(), user_owed_amount.get(user.getId()) -
                        expense.getPaidBy().get(user));
            }
        }

        TreeSet<Pair<Long, Long>> set = new TreeSet<>(new Comparator<Pair<Long, Long>>() {
            @Override
            public int compare(Pair<Long, Long> o1, Pair<Long, Long> o2) {
                return (int)(o1.getFirst() - o2.getFirst());
            }
        }); // amount -> userID

        for (Long userId: user_owed_amount.keySet()) {
            set.add(Pair.of(user_owed_amount.get(userId), userId));
        }

        List<Transaction> transactions = new ArrayList<>();

        while (set.size() > 1) {
            Pair<Long, Long> smallestPair = set.first();
            Pair<Long, Long> largestPair = set.last();

            Transaction transaction = new Transaction();
            transaction.setFromId(largestPair.getSecond());
            transaction.setToId(smallestPair.getSecond());
            transaction.setAmount(largestPair.getFirst());

            set.remove(largestPair);
            set.remove(smallestPair);

            set.add(Pair.of(smallestPair.getFirst() + largestPair.getFirst(), smallestPair.getSecond()));
            transactions.add(transaction);
        }

        return transactions;
    }
}
