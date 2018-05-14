package gov.samhsa.ocp.smartauth.service.mapping;

import gov.samhsa.ocp.smartauth.domain.Launch;
import gov.samhsa.ocp.smartauth.service.dto.LaunchResponseDto;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class LaunchToLaunchResponseDtoMap extends PropertyMap<Launch, LaunchResponseDto> {
    @Override
    protected void configure() {
        map().setLaunch(source.getId());
    }
}
