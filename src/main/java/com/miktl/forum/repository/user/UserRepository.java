package com.miktl.forum.repository.user;

import com.miktl.forum.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
