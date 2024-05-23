package com.abl.rjmdb.service;

import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import com.abl.rjmdb.persistance.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private ModelMapper mapper;
    @Mock
    private UserRepository userRepository;

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
    public void signup_sanity_savedSuccessfully() {
        long id = 1L;
        String a = "a", b = "b", c = "c", d = "d";
        UserSignupInfo signupInfo = new UserSignupInfo(a, b, c, d, "e");
        UsersRecord record = new UsersRecord(id, a, b, c);
        User user = new User(id, a, b, c);

        doReturn(record).when(mapper).map(signupInfo, UsersRecord.class);
        doReturn(record).when(userRepository).save(record);
        doReturn(user).when(mapper).map(record, User.class);

        Mono<User> output = userService.signup(signupInfo);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(mapper).map(signupInfo, UsersRecord.class);
        verify(userRepository).save(record);
        verify(mapper).map(record, User.class);
    }

    @Test
    public void signup_mappingToRecordFailed_monoExpectError() {
        String a = "a", b = "b", c = "c", d = "d";
        UserSignupInfo signupInfo = new UserSignupInfo(a, b, c, d, "e");

        doThrow(new RuntimeException()).when(mapper).map(signupInfo, UsersRecord.class);

        Mono<User> output = userService.signup(signupInfo);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(signupInfo, UsersRecord.class);
    }

    @Test
    public void signup_savingToDbFailed_monoExpectError() {
        long id = 1L;
        String a = "a", b = "b", c = "c", d = "d";
        UserSignupInfo signupInfo = new UserSignupInfo(a, b, c, d, "e");
        UsersRecord record = new UsersRecord(id, a, b, c);

        doReturn(record).when(mapper).map(signupInfo, UsersRecord.class);
        doThrow(new RuntimeException()).when(userRepository).save(record);

        Mono<User> output = userService.signup(signupInfo);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(signupInfo, UsersRecord.class);
        verify(userRepository).save(record);
    }

    @Test
    public void signup_mappingToUserFailed_monoExpectError() {
        long id = 1L;
        String a = "a", b = "b", c = "c", d = "d";
        UserSignupInfo signupInfo = new UserSignupInfo(a, b, c, d, "e");
        UsersRecord record = new UsersRecord(id, a, b, c);

        doReturn(record).when(mapper).map(signupInfo, UsersRecord.class);
        doReturn(record).when(userRepository).save(record);
        doThrow(new RuntimeException()).when(mapper).map(record, User.class);

        Mono<User> output = userService.signup(signupInfo);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(signupInfo, UsersRecord.class);
        verify(userRepository).save(record);
        verify(mapper).map(record, User.class);
    }

    @Test
    public void update_sanity_savedSuccessfully() {
        long id = 1L;
        String a = "a", b = "b", c = "c", d = "d";
        UserSignupInfo signupInfo = new UserSignupInfo(a, b, c, d, "e");
        UsersRecord record = new UsersRecord(id, a, b, c);
        User user = new User(id, a, b, c);

        doReturn(record).when(mapper).map(signupInfo, UsersRecord.class);
        doReturn(record).when(userRepository).update(record);
        doReturn(user).when(mapper).map(record, User.class);

        Mono<User> output = userService.update(signupInfo);

        StepVerifier.create(output)
                .expectNextCount(1)
                .verifyComplete();

        verify(mapper).map(signupInfo, UsersRecord.class);
        verify(userRepository).update(record);
        verify(mapper).map(record, User.class);
    }

    @Test
    public void update_mappingToRecordFailed_monoExpectError() {
        String a = "a", b = "b", c = "c", d = "d";
        UserSignupInfo signupInfo = new UserSignupInfo(a, b, c, d, "e");

        doThrow(new RuntimeException()).when(mapper).map(signupInfo, UsersRecord.class);

        Mono<User> output = userService.update(signupInfo);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(signupInfo, UsersRecord.class);
    }

    @Test
    public void update_savingToDbFailed_monoExpectError() {
        long id = 1L;
        String a = "a", b = "b", c = "c", d = "d";
        UserSignupInfo signupInfo = new UserSignupInfo(a, b, c, d, "e");
        UsersRecord record = new UsersRecord(id, a, b, c);

        doReturn(record).when(mapper).map(signupInfo, UsersRecord.class);
        doThrow(new RuntimeException()).when(userRepository).update(record);

        Mono<User> output = userService.update(signupInfo);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(signupInfo, UsersRecord.class);
        verify(userRepository).update(record);
    }

    @Test
    public void update_mappingToUserFailed_monoExpectError() {
        long id = 1L;
        String a = "a", b = "b", c = "c", d = "d";
        UserSignupInfo signupInfo = new UserSignupInfo(a, b, c, d, "e");
        UsersRecord record = new UsersRecord(id, a, b, c);

        doReturn(record).when(mapper).map(signupInfo, UsersRecord.class);
        doReturn(record).when(userRepository).update(record);
        doThrow(new RuntimeException()).when(mapper).map(record, User.class);

        Mono<User> output = userService.update(signupInfo);

        StepVerifier.create(output)
                .verifyError();

        verify(mapper).map(signupInfo, UsersRecord.class);
        verify(userRepository).update(record);
        verify(mapper).map(record, User.class);
    }
}
