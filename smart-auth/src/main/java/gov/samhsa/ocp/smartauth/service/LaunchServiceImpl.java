package gov.samhsa.ocp.smartauth.service;

import gov.samhsa.ocp.smartauth.domain.Launch;
import gov.samhsa.ocp.smartauth.domain.LaunchRepository;
import gov.samhsa.ocp.smartauth.service.dto.LaunchRequestDto;
import gov.samhsa.ocp.smartauth.service.dto.LaunchResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaunchServiceImpl implements LaunchService {

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LaunchResponseDto create(LaunchRequestDto launchRequest) {
        final Launch launch = Launch.builder()
                .user(launchRequest.getUser())
                .build();
        final Launch savedLaunch = launchRepository.save(launch);
        final LaunchResponseDto launchResponse = modelMapper.map(savedLaunch, LaunchResponseDto.class);
        return launchResponse;
    }
}
