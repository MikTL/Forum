package com.miktl.forum.repository.topic;

import com.miktl.forum.domain.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
