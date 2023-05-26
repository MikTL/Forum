package com.miktl.forum.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisteredDataTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long id_author,
        @NotNull
        Long id_course

) {}
