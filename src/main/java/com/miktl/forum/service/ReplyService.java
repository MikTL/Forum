package com.miktl.forum.service;

import com.miktl.forum.domain.reply.Reply;
import com.miktl.forum.domain.topic.Topic;
import com.miktl.forum.domain.user.User;
import com.miktl.forum.dto.reply.DataToCreateReply;
import com.miktl.forum.repository.reply.ReplyRepository;
import com.miktl.forum.repository.topic.TopicRepository;
import com.miktl.forum.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReplyService {
    private ReplyRepository replyRepo;
    private TopicRepository topicRepo;
    private UserRepository userRepo;

    public ReplyService(
            ReplyRepository replyRepo,
            TopicRepository topicRepository,
            UserRepository userRepo
    ) {
        this.replyRepo=replyRepo;
        this.topicRepo = topicRepository;
        this.userRepo = userRepo;
    }

    public void CreateReply(DataToCreateReply data) {
        Long topicId = data.topicId();
        Long userId = data.userId();
        Topic topic = topicRepo.findById(topicId).orElseThrow(
                ()-> new EntityNotFoundException("Reply not found with id: "+topicId));
        User user = userRepo.findById(userId).orElseThrow(
                        () -> new EntityNotFoundException("Reply not found with id: " + userId));
        Reply reply = new Reply();
        reply.setMessage(data.message());
        reply.setCreationDate(LocalDateTime.now());
        reply.setTopic(topic);
        reply.setUser(user);

        replyRepo.save(reply);
    }
}
