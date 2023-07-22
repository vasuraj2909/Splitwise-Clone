package com.scaler.splitwise.services;

import com.scaler.splitwise.models.User;
import com.scaler.splitwise.repositories.UserRepository;
import com.scaler.splitwise.services.passwordencoder.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String phoneNumber, String password) {
        // create password hash
        String encodedPassword = passwordEncoder.encode(password);
        // create user object
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setHashedPassword(encodedPassword);
        // save user object in the repository

        return userRepository.save(user);
    }

    public User updateUserProfile(Long id, String name, String phoneNumber, String password) {
        Optional<User> optionalUseruser = userRepository.findById(id);
        User user = optionalUseruser.get();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setHashedPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }
}
