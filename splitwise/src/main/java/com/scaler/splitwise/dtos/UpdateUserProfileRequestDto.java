package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileRequestDto{
    private long userId;
    private String name;
    private String phoneNumber;
    private String password;
}
