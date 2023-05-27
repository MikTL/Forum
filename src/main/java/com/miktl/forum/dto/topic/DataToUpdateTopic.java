package com.miktl.forum.dto.topic;

import com.miktl.forum.domain.topic.StatusTopic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataToUpdateTopic(
    @NotBlank
    String title,
    @NotBlank
    String message,
    @NotNull
    StatusTopic statusTopic
) {}
