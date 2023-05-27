package com.miktl.forum.dto.topic;

import com.miktl.forum.domain.topic.StatusTopic;

import java.time.LocalDateTime;

public record TopicListingData(
        String title,
        String message,
        LocalDateTime creationDate,
        StatusTopic statusTopic,
        String author,
        String course
) {

}
