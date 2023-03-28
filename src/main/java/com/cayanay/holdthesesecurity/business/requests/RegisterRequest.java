package com.cayanay.holdthesesecurity.business.requests;

import com.cayanay.holdthesesecurity.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private UserRole role;
}
