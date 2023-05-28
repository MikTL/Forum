package com.miktl.forum.repository.topic;

import com.miktl.forum.domain.topic.Topic;
import com.miktl.forum.dto.topic.TopicListingData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Long> {
    @Query("SELECT new com.miktl.forum.dto.topic.TopicListingData(t.id,t.title,t.message,t.creationDate,t.statusTopic,u.name,c.name) FROM Topic t JOIN User u ON t.id_user= u.id JOIN Course c ON t.id_course=c.id")
    Page<TopicListingData> findAllWithAuthorAndCourse(Pageable pageable);

}
