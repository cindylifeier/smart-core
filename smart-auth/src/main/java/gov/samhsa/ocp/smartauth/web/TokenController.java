package gov.samhsa.ocp.smartauth.web;

import gov.samhsa.ocp.smartauth.domain.GrantType;
import gov.samhsa.ocp.smartauth.domain.Param;
import gov.samhsa.ocp.smartauth.infrastructure.OAuth2TokenRestClient;
import gov.samhsa.ocp.smartauth.infrastructure.dto.TokenResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private OAuth2TokenRestClient oAuth2TokenRestClient;

    @PostMapping("/token")
    public TokenResponseDto getToken(@RequestHeader("Authorization") String basicAuth,
                                     @RequestParam(Param.GRANT_TYPE) GrantType grantType,
                                     @RequestParam(Param.CODE) String code,
                                     @RequestParam(Param.CLIENT_ID) String clientId,
                                     @RequestParam(Param.REDIRECT_URI) String redirectUri) {
        return oAuth2TokenRestClient.getToken(
                basicAuth,
                grantType,
                code,
                clientId,
                redirectUri);
    }
}
