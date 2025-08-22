package cl.smart.job.exercise.bci.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import cl.smart.job.exercise.bci.commons.PasswordValidator;
import cl.smart.job.exercise.bci.controller.data.UserRegisterRequest;
import cl.smart.job.exercise.bci.controller.data.UserRegisterResponse;
import cl.smart.job.exercise.bci.exceptions.BciUserException;
import cl.smart.job.exercise.bci.repository.LoginAccessRepository;
import cl.smart.job.exercise.bci.repository.UserRepository;
import cl.smart.job.exercise.bci.repository.entity.LoginAccessEntity;
import cl.smart.job.exercise.bci.repository.entity.UserEntity;
import cl.smart.job.exercise.bci.security.JwtUtil;
import cl.smart.job.exercise.bci.service.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private LoginAccessRepository loginAccessRepository;
    @Mock
    private PasswordValidator passwordValidator;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private JwtUtil jwtUtil;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Successful registration")
    void registerUserSuccessTest() {
        UserRegisterRequest request = UserRegisterRequest.builder()
                .password("Elijah")
                .email("elijah@gmail.com")
                .build();
        UserEntity responseEntity = new UserEntity();
        responseEntity.setId(1);
        responseEntity.setUserName("username");
        responseEntity.setCreationDate(LocalDateTime.now());

        when(passwordValidator.isValid(anyString())).thenReturn(true);
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userMapper.userRegisterRequestToUserEntity(any(UserRegisterRequest.class))).thenReturn(new UserEntity());
        when(userRepository.save(any(UserEntity.class))).thenReturn(responseEntity);
        when(jwtUtil.generateToken(anyString())).thenReturn("token");
        when(jwtUtil.extractExpiration(anyString())).thenReturn(Instant.now());
        when(loginAccessRepository.save(any(LoginAccessEntity.class))).thenReturn(LoginAccessEntity.builder().build());

        UserRegisterResponse response = userService.registerUser(request);

        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getCreated()).isNotNull();
        assertThat(response.getToken()).isNotNull();
    }

    @Test
    @DisplayName("invalid password format")
    void registerUserFormatInvalidPasswordTest() {
        UserRegisterRequest request = UserRegisterRequest.builder()
                .password("eelijah")
                .email("elijah@gmail.com")
                .build();

        when(passwordValidator.isValid(anyString())).thenReturn(false);

        assertThatThrownBy(()-> userService.registerUser(request))
                .isInstanceOf(BciUserException.class);
    }

    @Test
    @DisplayName("Mail already exists in the DB")
    void registerUserExistingEmailTest() {
        UserRegisterRequest request = UserRegisterRequest.builder()
                .password("Elijah")
                .email("elijah@gmail.com")
                .build();
        UserEntity responseEntity = new UserEntity();
        responseEntity.setId(1);
        responseEntity.setCreationDate(LocalDateTime.now());

        when(passwordValidator.isValid(anyString())).thenReturn(true);
        when(userRepository.findByEmail(anyString())).thenReturn(new UserEntity());

        assertThatThrownBy(()-> userService.registerUser(request))
                .isInstanceOf(BciUserException.class);
    }
}
