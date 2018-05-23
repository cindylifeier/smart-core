package gov.samhsa.ocp.smartcore.infrastructure;

import gov.samhsa.ocp.smartcore.domain.GrantType;
import gov.samhsa.ocp.smartcore.infrastructure.dto.TokenResponseDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static gov.samhsa.ocp.smartcore.config.Constants.AUTHORIZATION_HEADER;
import static gov.samhsa.ocp.smartcore.config.Constants.CODE;
import static gov.samhsa.ocp.smartcore.config.Constants.GRANT_TYPE;
import static gov.samhsa.ocp.smartcore.config.Constants.REDIRECT_URI;

@FeignClient(name = "oauth2TokenRestClient", url = "${smart-core.oauth2-token}")
public interface OAuth2TokenRestClient {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    TokenResponseDto getToken(@RequestHeader(AUTHORIZATION_HEADER) String basicAuth,
                              @RequestParam(GRANT_TYPE) GrantType grantType,
                              @RequestParam(CODE) String code,
                              @RequestParam(REDIRECT_URI) String redirectUri);
}
