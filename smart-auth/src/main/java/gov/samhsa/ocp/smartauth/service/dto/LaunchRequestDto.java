package gov.samhsa.ocp.smartauth.service.dto;

import lombok.Data;

@Data
public class LaunchRequestDto {
    private String user;
    private String organization;
    private String location;
    private String patient;
    private String encounter;
    private String resource;
}
