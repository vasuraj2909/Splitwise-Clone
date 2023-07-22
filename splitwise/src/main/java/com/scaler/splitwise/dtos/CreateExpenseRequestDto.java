package com.scaler.splitwise.dtos;


import com.scaler.splitwise.models.Currency;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreateExpenseRequestDto {
    private long userId;
    private String description;
    private long amount;
    private Currency currency;
    private Map<Long, Long> paidBy; // Id -> Amount
    private Map<Long, Long> owedBy; // Id -> Amount
}
