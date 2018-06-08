package gov.samhsa.ocp.smartcore.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import gov.samhsa.ocp.smartcore.service.dto.ClientType;
import lombok.Data;

import static gov.samhsa.ocp.smartcore.config.Constants.AUTHORIZATION_CODE;

@Data
public class ClientDto {
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("authorized_grant_types")
    private String[] authorizedGrantTypes ={AUTHORIZATION_CODE};

    @JsonProperty("redirect_uri")
    private String[] redirectUri;

    private String[] scope;

    private String name;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("client_type")
    private ClientType clientType;
}
