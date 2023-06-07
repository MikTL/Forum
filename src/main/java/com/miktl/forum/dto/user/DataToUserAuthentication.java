package com.miktl.forum.dto.user;

import jakarta.validation.constraints.NotBlank;

public record DataToUserAuthentication(@NotBlank String email, @NotBlank String password) {
}
