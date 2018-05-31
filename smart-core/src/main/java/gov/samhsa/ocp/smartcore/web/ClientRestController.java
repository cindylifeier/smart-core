package gov.samhsa.ocp.smartcore.web;

import gov.samhsa.ocp.smartcore.service.ClientService;
import gov.samhsa.ocp.smartcore.service.dto.ClientDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientRestController {
    @Autowired
    private ClientService clientService;

    @PostMapping()
    public void createClient(@Valid @RequestBody ClientDetailDto clientDto) {
        clientService.createClient(clientDto);
    }
}
