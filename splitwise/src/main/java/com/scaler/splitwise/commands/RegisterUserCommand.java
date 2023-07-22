package com.scaler.splitwise.commands;

import com.scaler.splitwise.controllers.UserController;
import com.scaler.splitwise.dtos.RegisterUserRequestDto;
import com.scaler.splitwise.dtos.RegisterUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class  RegisterUserCommand implements Command {
    private UserController userController;

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean canExecute(String input) {
        // Register vinsmokesanji 003 namisswwaann
        // ["Register", "vinsmokesanji", "003", "namisswwaann"]
        List<String> params = Arrays.stream(input.split(" ")).toList();

        if (params.size() != 4) {
            return false;
        }

        if (!params.get(0).equals(CommandKeywords.REGISTER_USER_COMMAND)) {
            return false;
        }

        return true;
    }

    @Override
    public void execute(String input) {
        List<String> commandTokens = Arrays.stream(input.split(" ")).toList();
        String username = commandTokens.get(1);
        String phoneNumber = commandTokens.get(2);
        String password = commandTokens.get(3);

        RegisterUserRequestDto registerUserRequestDto = new RegisterUserRequestDto();
        registerUserRequestDto.setPassword(password);
        registerUserRequestDto.setName(username);
        registerUserRequestDto.setPhoneNumber(phoneNumber);

        RegisterUserResponseDto response = userController.registerUser(registerUserRequestDto);

        System.out.println(response.getUser());
        System.out.println("User Registered");
    }
}
