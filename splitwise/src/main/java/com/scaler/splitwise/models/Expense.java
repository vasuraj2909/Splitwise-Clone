package com.scaler.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expense extends BaseModel {
    private String description;

    @ManyToOne
    private User createdBy;

    private long amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ElementCollection
    Map<User, Long> paidBy;

    @ElementCollection
    Map<User, Long> owedBy;
}