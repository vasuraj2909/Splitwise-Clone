package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleGroupExpenseResponseDto extends ResponseDto {
    private List<Transaction>  transactions;
}