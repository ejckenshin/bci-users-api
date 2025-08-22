package cl.smart.job.exercise.bci.controller;

import cl.smart.job.exercise.bci.commons.KeyMessage;
import cl.smart.job.exercise.bci.controller.data.UserRegisterRequest;
import cl.smart.job.exercise.bci.controller.data.UserRegisterResponse;
import cl.smart.job.exercise.bci.exceptions.ErrorResponse;
import cl.smart.job.exercise.bci.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to user management.")
public class UserController {

    private final UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = KeyMessage.OPERATION_SUMMARY_REGISTER_USER,
            description = KeyMessage.OPERATION_DESCRIPTION_REGISTER_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = KeyMessage.REGISTRATION_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = UserRegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = KeyMessage.BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "412", description = KeyMessage.PRECONDITION_FAILED,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = KeyMessage.ERROR_GENERIC,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserRegisterResponse> registerUser(
            @RequestBody @Valid UserRegisterRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }

}
