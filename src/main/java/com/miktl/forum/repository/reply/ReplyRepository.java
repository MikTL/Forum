package com.miktl.forum.repository.reply;

import com.miktl.forum.domain.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
