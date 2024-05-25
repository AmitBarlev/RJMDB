package com.abl.rjmdb.service;

import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import com.abl.rjmdb.model.jooq.tables.records.ClientRecord;
import com.abl.rjmdb.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository repository;

    public Mono<User> signup(UserSignupInfo info) {
        return Mono.fromCallable(() -> modelMapper.map(info, ClientRecord.class))
                .doOnNext(repository::save)
                .map(record -> modelMapper.map(record, User.class))
                .doOnError(x -> log.error("User signup failed"));
    }

    public Mono<User> update(UserSignupInfo info) {
        return Mono.fromCallable(() -> modelMapper.map(info, ClientRecord.class))
                .doOnNext(repository::update)
                .map(record -> modelMapper.map(record, User.class))
                .doOnError(x -> log.error("User signup failed"));
    }
}
