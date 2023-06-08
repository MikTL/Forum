package com.miktl.forum.controller;

import com.miktl.forum.domain.user.User;
import com.miktl.forum.dto.user.DataToUserAuthentication;
import com.miktl.forum.security.JwtTokenData;
import com.miktl.forum.security.TokenService;
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
    private TokenService tokenService;
    public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }
    @PostMapping
    @Transactional
    public ResponseEntity userAuthentication(@RequestBody @Valid DataToUserAuthentication dataAuthentication){
        Authentication authToken =
                new UsernamePasswordAuthenticationToken(
                        dataAuthentication.email(),
                        dataAuthentication.password()
                );
        var AuthenticateUser = manager.authenticate(authToken);
        String JWTToken = tokenService.tokenGeneration((User) AuthenticateUser.getPrincipal());
        return ResponseEntity .ok(new JwtTokenData(JWTToken));
    }
}
