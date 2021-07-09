package com.security.springsecurity.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String userName;
    private String password;
    private String token;
}
