package com.miktl.forum.service;

import com.miktl.forum.domain.user.User;
import com.miktl.forum.dto.user.DataToRegisterUser;
import com.miktl.forum.dto.user.RegisteredDataUser;
import com.miktl.forum.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public RegisteredDataUser registerUser(DataToRegisterUser dataUser) {
        User user= userRepository.save(new User(dataUser));
        return new RegisteredDataUser(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public void deleteUser(Long id) {
        User user = userRepository.getReferenceById(id);
        user.logicDelete();
    }
}
