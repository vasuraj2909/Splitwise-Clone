package com.scaler.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserProfileResponseDto extends ResponseDto {
    private UserResponseDto user;
}