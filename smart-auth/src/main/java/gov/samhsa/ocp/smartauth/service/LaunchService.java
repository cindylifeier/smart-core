package gov.samhsa.ocp.smartauth.service;

import gov.samhsa.ocp.smartauth.service.dto.LaunchRequestDto;
import gov.samhsa.ocp.smartauth.service.dto.LaunchResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface LaunchService {
    @Transactional
    LaunchResponseDto create(LaunchRequestDto launchRequest);

    @Transactional
    LaunchResponseDto mergeAndSave(String launchId, LaunchRequestDto launchRequest);

    @Transactional(readOnly = true)
    LaunchResponseDto get(String launchId);
}
