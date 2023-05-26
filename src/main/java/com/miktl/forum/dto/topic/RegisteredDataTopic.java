package com.miktl.forum.dto.topic;

import com.miktl.forum.domain.topic.StatusTopic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegisteredDataTopic(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        StatusTopic statusTopic,
        String author,
        String course

) {}
