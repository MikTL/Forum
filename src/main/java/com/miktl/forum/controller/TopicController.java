package com.miktl.forum.controller;

import com.miktl.forum.dto.topic.DataToRegisterTopic;
import com.miktl.forum.dto.topic.RegisteredDataTopic;
import com.miktl.forum.dto.topic.TopicListingData;
import com.miktl.forum.repository.topic.TopicRepository;
import com.miktl.forum.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;
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
    @GetMapping
    public ResponseEntity<Page<TopicListingData>> ListTopics(@PageableDefault(size = 10)Pageable pageable){
        return ResponseEntity.ok(topicService.ListTopics(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisteredDataTopic> viewTopic(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.viewTopic(id));
    }
}
