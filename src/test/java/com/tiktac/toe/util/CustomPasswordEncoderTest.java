package com.tiktac.toe.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class CustomPasswordEncoderTest {

    private CustomPasswordEncoder customPasswordEncoder;

    @BeforeEach
    void setUp() {
        this.customPasswordEncoder = new CustomPasswordEncoder();
    }

    @Test
    void getPasswordEncoder() {
        assertInstanceOf(
                PasswordEncoder.class,
                this.customPasswordEncoder.getPasswordEncoder()
        );
    }
}