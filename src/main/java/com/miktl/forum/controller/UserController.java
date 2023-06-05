package com.miktl.forum.controller;

import com.miktl.forum.dto.user.DataToRegisterUser;
import com.miktl.forum.dto.user.RegisteredDataUser;
import com.miktl.forum.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService=userService;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<RegisteredDataUser> registerUser(
            @RequestBody
            @Valid
            DataToRegisterUser dataToRegisterUser,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        RegisteredDataUser registeredDataUser=userService.registerUser(dataToRegisterUser);
        URI uri = uriComponentsBuilder.path("user/{id}").buildAndExpand(registeredDataUser.id()).toUri();
        return ResponseEntity.created(uri).body(registeredDataUser) ;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
