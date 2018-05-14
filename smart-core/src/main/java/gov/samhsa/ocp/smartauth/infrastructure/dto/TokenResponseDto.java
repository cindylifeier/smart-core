package gov.samhsa.ocp.smartauth.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponseDto {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("expires_in")
    private int expiresIn;
    private String scope;
    @JsonProperty("ext_attr")
    private Map<String, String> extraAttributes;
    private String jti;
    // SMART Context
    private String launch;
    private String user;
    private String organization;
    private String location;
    private String patient;
    private String encounter;
    private String resource;
    @JsonProperty("need_patient_banner")
    private Boolean needPatientBanner;
    private String intent;
    @JsonProperty("smart_style_url")
    private String smartStyleUrl;
}
