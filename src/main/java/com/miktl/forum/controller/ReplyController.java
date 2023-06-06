package com.miktl.forum.controller;

import com.miktl.forum.dto.reply.DataToCreateReply;
import com.miktl.forum.service.ReplyService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    private ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping
    @Transactional
    public void createReply(@RequestBody @Valid DataToCreateReply data) {
        replyService.CreateReply(data);
    }
}
