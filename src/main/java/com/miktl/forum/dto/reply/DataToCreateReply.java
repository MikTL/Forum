package com.miktl.forum.dto.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataToCreateReply(
        @NotBlank
        String message,
        @NotNull
        Long topicId,
        @NotNull
        Long userId
) {}
