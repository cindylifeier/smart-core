package gov.samhsa.ocp.smartcore.service;

import gov.samhsa.ocp.smartcore.service.dto.ClientDetailDto;
import org.springframework.transaction.annotation.Transactional;

public interface ClientService {
    @Transactional
    void createClient(ClientDetailDto clientDto);
}
