package gov.samhsa.ocp.smartcore.service;

import gov.samhsa.ocp.smartcore.domain.GrantType;
import gov.samhsa.ocp.smartcore.infrastructure.dto.TokenResponseDto;

import java.util.Optional;

public interface TokenService {
    TokenResponseDto getToken(Optional<String> basicAuth,
                              GrantType grantType,
                              String code,
                              Optional<String> clientId,
                              String redirectUri);
}
