package gov.samhsa.ocp.smartcore.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@ConfigurationProperties(prefix = "smart-core")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmartCoreProperties {
    @Valid
    @NotBlank
    private String contextInitializer;

    @Valid
    @NotBlank
    private String oauth2;

    @Valid
    @NotBlank
    private String oauth2Authorize;

    @Valid
    @NotBlank
    private String oauth2Token;
}
