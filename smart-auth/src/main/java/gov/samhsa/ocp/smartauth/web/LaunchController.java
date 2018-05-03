package gov.samhsa.ocp.smartauth.web;

import gov.samhsa.ocp.smartauth.service.LaunchService;
import gov.samhsa.ocp.smartauth.service.dto.LaunchRequestDto;
import gov.samhsa.ocp.smartauth.service.dto.LaunchResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/launches")
public class LaunchController {
    @Autowired
    private LaunchService launchService;

    @PostMapping()
    public LaunchResponseDto create(@Valid @RequestBody LaunchRequestDto launchRequest) {
        return launchService.create(launchRequest);
    }
}
