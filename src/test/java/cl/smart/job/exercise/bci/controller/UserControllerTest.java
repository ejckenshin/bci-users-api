package cl.smart.job.exercise.bci.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.smart.job.exercise.bci.controller.data.PhoneRequest;
import cl.smart.job.exercise.bci.controller.data.UserRegisterRequest;
import cl.smart.job.exercise.bci.controller.data.UserRegisterResponse;
import cl.smart.job.exercise.bci.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Successful registration")
    @Test
    void registerUserSuccessTest() throws Exception {
        LocalDateTime dateCreate = LocalDateTime.now();
        UserRegisterResponse response = UserRegisterResponse.builder()
                .id(1)
                .created(dateCreate)
                .build();
        UserRegisterRequest request = UserRegisterRequest.builder()
                .email("test@gmail.com")
                .name("elijah Casas")
                .password("abcde")
                .phones(Collections.singletonList(PhoneRequest.builder()
                                .number("12345")
                                .cityCode("1")
                                .countryCode("57")
                        .build()))
                .build();
        when(userService.registerUser(any(UserRegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

    }

    @DisplayName("Incorrect, empty or non-sent input data")
    @Test
    void registerUserBadRequestTest() throws Exception {
        UserRegisterRequest request = UserRegisterRequest.builder()
                .email("test@gmail.com")
                .password("")
                .phones(Collections.singletonList(PhoneRequest.builder()
                        .number("12345")
                        .cityCode("1")
                        .countryCode("57")
                        .build()))
                .build();

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }

    @DisplayName("Required request body is missing")
    @Test
    void registerUserRequestBodyMissingTest() throws Exception {

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());

    }

}
