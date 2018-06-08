package gov.samhsa.ocp.smartcore.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientDetailDto {
    @NotNull
    @JsonProperty("client_type")
    private ClientType clientType;
    @NotNull
    @JsonProperty("client_id")
    private String clientId;
    @NotNull
    @JsonProperty("redirect_uri")
    private String[] redirectUri;
    @NotNull
    private String[] scope;
    @NotNull
    private String name;

    @JsonProperty("client_secret")
    private String clientSecret;
    @NotNull
    private String appLaunchUrl;
    private String appIcon;
}
