package gov.samhsa.ocp.smartcore.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientDetailDto {
    @NotNull
    private ClientType client_type;
    @NotNull
    private String client_id;
    @NotNull
    private String[] redirect_uri;
    @NotNull
    private String[] scope;
    @NotNull
    private String name;
    private String client_secret;
    @NotNull
    private String appLaunchUrl;
    private String appIcon;
}
