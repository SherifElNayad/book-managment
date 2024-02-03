package com.example.bookmanagment.Security.auth;

import com.example.bookmanagment.Entities.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String password;
    private String email;
    private Role role;
}
