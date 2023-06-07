package com.miktl.forum.controller;

import com.miktl.forum.dto.user.DataToUserAuthentication;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    private AuthenticationManager manager;
    public AuthenticationController(AuthenticationManager manager) {
        this.manager = manager;
    }
    @PostMapping
    @Transactional
    public ResponseEntity userAuthentication(@RequestBody @Valid DataToUserAuthentication dataAuthentication){
        Authentication token =
                new UsernamePasswordAuthenticationToken(
                        dataAuthentication.email(),
                        dataAuthentication.password()
                );
        manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
