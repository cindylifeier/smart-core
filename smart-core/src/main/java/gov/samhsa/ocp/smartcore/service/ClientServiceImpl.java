package gov.samhsa.ocp.smartcore.service;

import gov.samhsa.ocp.smartcore.infrastructure.OAuth2ClientRestClient;
import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientMetaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private OAuth2ClientRestClient oAuth2ClientRestClient;

    @Override
    public List<ClientMetaDto> getAllClientMeta() {
        return oAuth2ClientRestClient.getAllClientMeta().stream()
                .filter(meta -> StringUtils.hasText(meta.getAppLaunchUrl()))
                .collect(Collectors.toList());
    }
}
