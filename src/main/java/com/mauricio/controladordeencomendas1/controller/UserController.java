package com.mauricio.controladordeencomendas1.controller;

import com.mauricio.controladordeencomendas1.model.User;
import com.mauricio.controladordeencomendas1.service.RegisterRequest;
import com.mauricio.controladordeencomendas1.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
}
