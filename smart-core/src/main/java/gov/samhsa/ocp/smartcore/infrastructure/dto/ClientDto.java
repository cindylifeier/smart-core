package gov.samhsa.ocp.smartcore.infrastructure.dto;

import gov.samhsa.ocp.smartcore.service.dto.ClientType;
import lombok.Data;

import static gov.samhsa.ocp.smartcore.config.Constants.AUTHORIZATION_CODE;

@Data
public class ClientDto {
    private String client_id;
    private String[] authorized_grant_types={AUTHORIZATION_CODE};
    private String[] redirect_uri;
    private String[] scope;
    private String name;
    private String client_secret;
    private ClientType client_type;
}
