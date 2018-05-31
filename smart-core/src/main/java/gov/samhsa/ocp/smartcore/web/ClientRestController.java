package gov.samhsa.ocp.smartcore.web;

import gov.samhsa.ocp.smartcore.service.ClientService;
import gov.samhsa.ocp.smartcore.service.dto.ClientDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import gov.samhsa.ocp.smartcore.infrastructure.dto.ClientMetaDto;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;


import java.util.List;

@RestController
public class ClientRestController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/clients")
    public void createClient(@Valid @RequestBody ClientDetailDto clientDto) {
        clientService.createClient(clientDto);
    }

    @GetMapping("/clients")
    public List<ClientMetaDto> getClients() {
        return clientService.getAllClientMeta();
    }

}
