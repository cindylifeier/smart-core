package gov.samhsa.ocp.smartauth.infrastructure;

import gov.samhsa.ocp.smartauth.domain.GrantType;
import gov.samhsa.ocp.smartauth.domain.Param;
import gov.samhsa.ocp.smartauth.infrastructure.dto.TokenResponseDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oauth2TokenRestClient", url = "${smart-auth.oauth2-token}")
public interface OAuth2TokenRestClient {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    TokenResponseDto getToken(@RequestHeader("Authorization") String basicAuth,
                              @RequestParam(Param.GRANT_TYPE) GrantType grantType,
                              @RequestParam(Param.CODE) String code,
                              @RequestParam(Param.CLIENT_ID) String clientId,
                              @RequestParam(Param.REDIRECT_URI) String redirectUri);
}
