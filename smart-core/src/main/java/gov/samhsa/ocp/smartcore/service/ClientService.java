package gov.samhsa.ocp.smartcore.service;

import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientMetaDto;

import java.util.List;

public interface ClientService {
    List<ClientMetaDto> getAllClientMeta();
}
