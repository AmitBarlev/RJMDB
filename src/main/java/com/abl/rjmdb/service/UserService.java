package com.abl.rjmdb.service;

import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    public Mono<User> signup(UserSignupInfo info) {
        throw new UnsupportedOperationException();
    }
}
