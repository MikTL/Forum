package com.miktl.forum.dto.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DataToRegisterUser(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
