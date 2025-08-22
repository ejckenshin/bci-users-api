package cl.smart.job.exercise.bci.controller.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserRegisterResponse {
    private Integer id;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("isactive")
    private boolean active;
    private String token;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private LocalDateTime modified;

}
