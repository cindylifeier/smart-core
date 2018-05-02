package gov.samhsa.ocp.smartauth.service;

import gov.samhsa.ocp.smartauth.service.dto.LaunchRequestDto;
import gov.samhsa.ocp.smartauth.service.dto.LaunchResponseDto;

public interface LaunchService {
    LaunchResponseDto create(LaunchRequestDto launchRequest);
}
