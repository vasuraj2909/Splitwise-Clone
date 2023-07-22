package com.scaler.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group extends BaseModel {
    private String name;

    @ManyToOne
    private User admin;

    @ManyToMany
    private List<User> members;

    @OneToMany
    private List<Expense> expenses;
}
