package com.abl.controller;

import com.abl.model.User;
import com.abl.service.UserService;
import com.abl.model.UserSignupInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    @SneakyThrows
    void tearDown() {
        closeable.close();
    }

    @Test
    public void signup_sanity_monoWithValue()  {
        UserSignupInfo signupInfo = new UserSignupInfo();
        User user = new User();

        doReturn(Mono.just(user)).when(userService).signup(signupInfo);

        Mono<User> output = userController.signup(signupInfo);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(userService).signup(signupInfo);
    }

    @Test
    public void update_sanity_monoWithValue()  {
        UserSignupInfo signupInfo = new UserSignupInfo();
        User user = new User();

        doReturn(Mono.just(user)).when(userService).update(signupInfo);

        Mono<User> output = userController.update(signupInfo);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(userService).update(signupInfo);
    }
}
