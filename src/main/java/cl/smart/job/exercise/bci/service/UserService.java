package cl.smart.job.exercise.bci.service;

import cl.smart.job.exercise.bci.controller.data.UserRegisterRequest;
import cl.smart.job.exercise.bci.controller.data.UserRegisterResponse;

public interface UserService {

    UserRegisterResponse registerUser(UserRegisterRequest request);
}
