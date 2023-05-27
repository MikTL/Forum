package com.miktl.forum.domain.topic;

import com.miktl.forum.dto.topic.DataToRegisterTopic;
import com.miktl.forum.dto.topic.DataToUpdateTopic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topic")
@Table(name = "topics")
@NoArgsConstructor
@Getter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private StatusTopic statusTopic;
    private Long id_user;
    private Long id_course;

    public Topic(DataToRegisterTopic dataToRegisterTopic) {
        this.title= dataToRegisterTopic.title();
        this.message= dataToRegisterTopic.message();
        this.creationDate= LocalDateTime.now();
        this.statusTopic= StatusTopic.OPEN;
        this.id_user=dataToRegisterTopic.id_author();
        this.id_course=dataToRegisterTopic.id_course();
    }

    public void updateData(DataToUpdateTopic topic) {
        if(topic.title()!=null) this.title=topic.title();
        if(topic.message()!=null) this.message=topic.message();
        if(topic.statusTopic()!=null) this.statusTopic=topic.statusTopic();
    }
}
