package gov.samhsa.ocp.smartcore.infrastructure;

import gov.samhsa.ocp.smartcore.config.OAuth2FeignClientCredentialsConfig;
import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientDto;
import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientMetaDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "oauth2ClientRestClient", url = "${smart-core.oauth2}", configuration = OAuth2FeignClientCredentialsConfig.class)
public interface OAuth2ClientRestClient {

    @RequestMapping(value = "/oauth/clients/{clientId}/meta", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ClientMetaDto getClientMeta(@PathVariable("clientId") String clientId);

    @RequestMapping(value = "/oauth/clients", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ClientDto createClient(ClientDto clientDto);


    @RequestMapping(value = "/oauth/clients/{clientId}/meta", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ClientMetaDto createClientMeta(@PathVariable("clientId") String clientId, ClientMetaDto clientMetaDto);
}
