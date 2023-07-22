package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String phoneNumber;
    private Long id;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
    }
}
