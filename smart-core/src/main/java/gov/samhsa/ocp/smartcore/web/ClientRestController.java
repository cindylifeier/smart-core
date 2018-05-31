package gov.samhsa.ocp.smartcore.web;

import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientMetaDto;
import gov.samhsa.ocp.smartcore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRestController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<ClientMetaDto> getClients() {
        return clientService.getAllClientMeta();
    }
}
