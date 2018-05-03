package gov.samhsa.ocp.smartauth.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "smart-auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmartAuthProperties {
    @Valid
    @NotNull
    private ContextInitializers contextInitializers;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ContextInitializers {
        @Valid
        @NotBlank
        private String user;
        @Valid
        @NotBlank
        private String organization;
        @Valid
        @NotBlank
        private String location;
        @Valid
        @NotBlank
        private String patient;
        @Valid
        @NotBlank
        private String encounter;
        @Valid
        @NotBlank
        private String resource;
    }
}
