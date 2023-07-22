package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.*;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    public RegisterUserResponseDto registerUser(RegisterUserRequestDto request) {
        User user = userService.registerUser(request.getName(), request.getPhoneNumber(), request.getPassword());

        RegisterUserResponseDto response = new RegisterUserResponseDto();
        response.setUser(new UserResponseDto(user));
        response.setRequestId(1234);
        response.setStatus(Status.SUCCESS);

        return response;
    }

    public UpdateUserProfileResponseDto updateUserProfile(UpdateUserProfileRequestDto request) {
        Long id = request.getUserId();
        String name = request.getName();
        String password = request.getPassword();
        String phoneNumber = request.getPhoneNumber();

        User user = userService.updateUserProfile(id, name, phoneNumber, password);;

        UpdateUserProfileResponseDto responseDto = new UpdateUserProfileResponseDto();
        responseDto.setUser(new UserResponseDto(user));
        responseDto.setStatus(Status.SUCCESS);

        return responseDto;
    }
}
