package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExpenseResponseDto extends ResponseDto {
    private Expense expense;
}
