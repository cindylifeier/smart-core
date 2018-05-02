package gov.samhsa.ocp.smartauth.service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LaunchRequestDto {
    @NotBlank
    private String user;
}
