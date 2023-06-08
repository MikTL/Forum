package com.miktl.forum.service;

import com.miktl.forum.domain.user.User;
import com.miktl.forum.dto.user.DataToRegisterUser;
import com.miktl.forum.dto.user.RegisteredDataUser;
import com.miktl.forum.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public RegisteredDataUser registerUser(DataToRegisterUser dataUser) {
        User user= new User(dataUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
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
