package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponseDto extends ResponseDto {
    private UserResponseDto user;
}
