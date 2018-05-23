package gov.samhsa.ocp.smartcore.service;

import gov.samhsa.ocp.smartcore.config.SmartCoreProperties;
import gov.samhsa.ocp.smartcore.domain.GrantType;
import gov.samhsa.ocp.smartcore.infrastructure.OAuth2TokenRestClient;
import gov.samhsa.ocp.smartcore.infrastructure.dto.TokenResponseDto;
import gov.samhsa.ocp.smartcore.service.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static gov.samhsa.ocp.smartcore.config.Constants.BASIC_AUTH_PREFIX;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private SmartCoreProperties smartCoreProperties;

    @Autowired
    private OAuth2TokenRestClient oAuth2TokenRestClient;

    @Override
    public TokenResponseDto getToken(Optional<String> basicAuth,
                                     GrantType grantType,
                                     String code,
                                     Optional<String> clientId,
                                     String redirectUri) {
        final TokenResponseDto tokenResponseDto = basicAuth
                .map(auth -> oAuth2TokenRestClient.getToken(auth, grantType, code, redirectUri))
                .orElseGet(() -> clientId
                        .map(client -> {
                            final String encodedBasic = Base64Utils.encodeToString((client + ":" + smartCoreProperties.getPublicClientSecret()).getBytes(StandardCharsets.UTF_8));
                            return oAuth2TokenRestClient.getToken(BASIC_AUTH_PREFIX + encodedBasic, grantType, code, redirectUri);
                        })
                        .orElseThrow(UnauthorizedException::new));
        return tokenResponseDto;
    }
}
