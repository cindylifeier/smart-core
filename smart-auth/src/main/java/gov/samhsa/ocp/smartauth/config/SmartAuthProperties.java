package gov.samhsa.ocp.smartauth.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@ConfigurationProperties(prefix = "smart-auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmartAuthProperties {
    @Valid
    @NotBlank
    private String contextInitializer;

    @Valid
    @NotBlank
    private String oauth2Authorization;
}
