package cl.smart.job.exercise.bci.service;

import cl.smart.job.exercise.bci.commons.KeyMessage;
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final LoginAccessRepository loginAccessRepository;
    private final PasswordValidator passwordValidator;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest request) {

        if (!passwordValidator.isValid(request.getPassword())) {
            throw new BciUserException(HttpStatus.BAD_REQUEST,
                    KeyMessage.MESSAGE_PASSWORD_NOT_VALID);
        }

        String email = request.getEmail().trim().toLowerCase();
        UserEntity entity = userRepository.findByEmail(email);
        if (Objects.nonNull(entity)) {
            throw new BciUserException(HttpStatus.PRECONDITION_FAILED,
                    KeyMessage.MESSAGE_EXISTING_MAIL,
                    Arrays.asList(email).toArray());
        }
        UserEntity responseEntity = userRepository.save(
                userMapper.userRegisterRequestToUserEntity(request));

        final String jwt = jwtUtil.generateToken(responseEntity.getUserName());
        LoginAccessEntity accessEntity = LoginAccessEntity.builder()
                .token(jwt)
                .userName(responseEntity.getUserName())
                .attempts(1)
                .expiryDate(jwtUtil.extractExpiration(jwt))
                .lastLogin(responseEntity.getCreationDate())
                .status("A")
                .build();
        loginAccessRepository.save(accessEntity);
        return UserRegisterResponse.builder()
                .id(responseEntity.getId())
                .userName(responseEntity.getUserName())
                .token(jwt)
                .active(true)
                .lastLogin(responseEntity.getCreationDate())
                .created(responseEntity.getCreationDate())
                .build();
    }
}
