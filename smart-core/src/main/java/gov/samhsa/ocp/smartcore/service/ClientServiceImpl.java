package gov.samhsa.ocp.smartcore.service;

import gov.samhsa.ocp.smartcore.infrastructure.OAuth2ClientRestClient;
import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientDto;
import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientMetaDto;
import gov.samhsa.ocp.smartcore.service.dto.ClientDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private OAuth2ClientRestClient oAuth2ClientRestClient;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createClient(ClientDetailDto clientDetailDto) {
        final ClientDto clientDto = oAuth2ClientRestClient.createClient(modelMapper.map(clientDetailDto, ClientDto.class));
        final ClientMetaDto clientMetaDto = oAuth2ClientRestClient.createClientMeta(clientDto.getClient_id(),modelMapper.map(clientDetailDto, ClientMetaDto.class));
    }
}
