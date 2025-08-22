package cl.smart.job.exercise.bci.controller.data;

import cl.smart.job.exercise.bci.commons.KeyMessage;
import cl.smart.job.exercise.bci.commons.RegularExpression;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
@Builder
public class UserRegisterRequest {

    @Pattern(regexp = RegularExpression.NAMES,
            message = KeyMessage.KEY_NAME_NOT_VALID)
    @NotBlank(message = KeyMessage.KEY_FIELD_NOT_BLANK)
    @Size(max = 50, message = KeyMessage.KEY_NAME_SIZE)
    private String name;

    @Pattern(regexp = RegularExpression.EMAIL,
            message = KeyMessage.KEY_EMAIL_NOT_VALID)
    @NotBlank(message = KeyMessage.KEY_FIELD_NOT_BLANK)
    @Size(max = 60, message = KeyMessage.KEY_NAME_SIZE)
    private String email;

    @NotBlank(message = KeyMessage.KEY_FIELD_NOT_BLANK)
    private String password;

    @Valid
    @NotNull(message = KeyMessage.KEY_FIELD_NOT_NULL)
    @NotEmpty(message = KeyMessage.KEY_LIST_NOT_EMPTY)
    private List<PhoneRequest> phones;
}
