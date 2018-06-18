package gov.samhsa.ocp.smartcore.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientDetailDto {
    @NotNull
    private ClientType clientType;
    @NotNull
    private String clientId;
    @NotNull
    private String[] redirectUri;
    @NotNull
    private String[] scope;
    @NotNull
    private String name;

    private String clientSecret;
    @NotNull
    private String appLaunchUrl;
    private String appIcon;
}
