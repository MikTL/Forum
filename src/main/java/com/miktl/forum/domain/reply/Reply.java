package com.miktl.forum.domain.reply;

import com.miktl.forum.domain.topic.Topic;
import com.miktl.forum.domain.user.User;
import com.miktl.forum.dto.reply.DataToCreateReply;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Reply")
@Table(name = "replies")
@Getter
@Setter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
