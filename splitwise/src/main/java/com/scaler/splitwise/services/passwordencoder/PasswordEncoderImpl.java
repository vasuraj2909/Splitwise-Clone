package com.scaler.splitwise.services.passwordencoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {

    private BCryptPasswordEncoder passwordEncoder;

    public PasswordEncoderImpl() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
