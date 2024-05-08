package com.abl.rjmdb.controller;

import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import com.abl.rjmdb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "signup")
    public Mono<User> signup(UserSignupInfo info) {
        return userService.signup(info);
    }

}
