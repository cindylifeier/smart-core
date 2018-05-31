package gov.samhsa.ocp.smartcore.infrastructure;

import gov.samhsa.ocp.smartcore.config.OAuth2FeignClientCredentialsConfig;
import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientMetaDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "oauth2ClientRestClient", url = "${smart-core.oauth2}", configuration = OAuth2FeignClientCredentialsConfig.class)
public interface OAuth2ClientRestClient {

    @RequestMapping(value = "/oauth/clients/meta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<ClientMetaDto> getAllClientMeta();

    @RequestMapping(value = "/oauth/clients/{clientId}/meta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ClientMetaDto getClientMeta(@PathVariable("clientId") String clientId);
}
