package cl.smart.job.exercise.bci.controller.data;

import cl.smart.job.exercise.bci.commons.KeyMessage;
import cl.smart.job.exercise.bci.commons.RegularExpression;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class PhoneRequest {

    @Pattern(regexp = RegularExpression.ONLY_DIGITS_NUMERICS,
            message = KeyMessage.KEY_FIELD_ONLY_DIGITS_NUMERICS)
    @NotBlank(message = KeyMessage.KEY_FIELD_NOT_BLANK)
    @Size(max = 12, message = KeyMessage.KEY_NAME_SIZE)
    private String number;

    @NotBlank(message = KeyMessage.KEY_FIELD_NOT_BLANK)
    @Size(max = 2, message = KeyMessage.KEY_NAME_SIZE)
    @JsonProperty("citycode")
    private String cityCode;

    @NotBlank(message = KeyMessage.KEY_FIELD_NOT_BLANK)
    @Size(max = 3, message = KeyMessage.KEY_NAME_SIZE)
    @JsonProperty("countrycode")
    private String countryCode;
}
