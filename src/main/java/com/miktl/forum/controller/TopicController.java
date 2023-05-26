package com.miktl.forum.controller;

import com.miktl.forum.dto.topic.DataToRegisterTopic;
import com.miktl.forum.dto.topic.RegisteredDataTopic;
import com.miktl.forum.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<RegisteredDataTopic>createTopic(
            @RequestBody
            @Valid
            DataToRegisterTopic dataToRegisterTopic,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        RegisteredDataTopic registeredDataTopic = topicService.createTopic(dataToRegisterTopic);
        URI uri= uriComponentsBuilder.path("/topic/{id}").buildAndExpand(registeredDataTopic.id()).toUri();
        return ResponseEntity.created(uri).body(registeredDataTopic);
    }
}
