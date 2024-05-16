package com.abl.rjmdb.service;

import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import com.abl.rjmdb.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository repository;

    public Mono<User> signup(UserSignupInfo info) {
        return Mono
                .just(modelMapper.map(info, UsersRecord.class))
                .map(repository::save)
                .map(record -> modelMapper.map(record, User.class))
                .doOnError(x -> log.error("User signup failed"));
    }

    public Mono<User> update(UserSignupInfo info) {
        return Mono
                .just(modelMapper.map(info, UsersRecord.class))
                .map(repository::update)
                .map(record -> modelMapper.map(record, User.class))
                .doOnError(x -> log.error("User signup failed"));
    }
}
