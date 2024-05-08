package com.abl.rjmdb;

import com.abl.rjmdb.controller.UserController;
import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import com.abl.rjmdb.service.UserService;
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
}
