package gov.samhsa.ocp.smartcore.service;

import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientMetaDto;
import gov.samhsa.ocp.smartcore.service.dto.ClientDetailDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientService {
    List<ClientMetaDto> getAllClientMeta();

    @Transactional
    List<ClientDetailDto> getAllClients();

    @Transactional
    void createClient(ClientDetailDto clientDto);

    @Transactional
    void deleteClient(String clientId);

    @Transactional
    void updateClient(String clientId, ClientDetailDto clientDto);
}
