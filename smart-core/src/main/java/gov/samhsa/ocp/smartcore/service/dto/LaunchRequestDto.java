package gov.samhsa.ocp.smartcore.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LaunchRequestDto {
    private String user;
    private String organization;
    private String location;
    private String patient;
    private String encounter;
    private String resource;
    @JsonProperty("need_patient_banner")
    private Boolean needPatientBanner;
    private String intent;
}
