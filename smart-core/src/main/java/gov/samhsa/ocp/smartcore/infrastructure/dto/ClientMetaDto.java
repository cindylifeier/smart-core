package gov.samhsa.ocp.smartcore.infrastructure.dto;

import lombok.Data;

@Data
public class ClientMetaDto {
    private String clientId;
    private String clientName;
    private boolean showOnHomePage;
    private String appLaunchUrl;
    private String appIcon;
}
