package cl.smart.job.exercise.bci.config;

import cl.smart.job.exercise.bci.commons.KeyMessage;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = KeyMessage.OPEN_API_TITLE,
        version = KeyMessage.OPEN_API_VERSION,
        description = KeyMessage.OPEN_API_DESCRIPTION
    )
)
public class OpenApiConfig {
}
