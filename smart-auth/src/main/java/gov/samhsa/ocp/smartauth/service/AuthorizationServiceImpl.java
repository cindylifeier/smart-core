package gov.samhsa.ocp.smartauth.service;

import gov.samhsa.ocp.smartauth.config.SmartAuthProperties;
import gov.samhsa.ocp.smartauth.domain.Launch;
import gov.samhsa.ocp.smartauth.domain.LaunchRepository;
import gov.samhsa.ocp.smartauth.service.exception.InvalidOrExpiredLaunchIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private SmartAuthProperties smartAuthProperties;

    @Override
    public Optional<String> getContextInitializerRedirectUri(String launch, String scopes) {
        final List<String> scope = Arrays.stream(scopes.split(" ")).filter(StringUtils::hasText).collect(toList());
        final Launch launchContext = launchRepository.findById(launch).orElseThrow(InvalidOrExpiredLaunchIdException::new);

        final boolean hasUser = StringUtils.hasText(launchContext.getUser());
        if (!hasUser) {
            return Optional.of(smartAuthProperties.getContextInitializers().getUser());
        }

        final boolean requiresOrganization = scope.contains("launch/organization");
        final boolean hasOrganization = StringUtils.hasText(launchContext.getOrganization());
        if (requiresOrganization && !hasOrganization) {
            return Optional.of(smartAuthProperties.getContextInitializers().getOrganization());
        }

        final boolean requiresLocation = scope.contains("launch/location");
        final boolean hasLocation = StringUtils.hasText(launchContext.getLocation());
        if (requiresLocation && !hasLocation) {
            return Optional.of(smartAuthProperties.getContextInitializers().getLocation());
        }

        final boolean requiresPatient = scope.contains("launch/patient");
        final boolean hasPatient = StringUtils.hasText(launchContext.getPatient());
        if (requiresPatient && !hasPatient) {
            return Optional.of(smartAuthProperties.getContextInitializers().getPatient());
        }

        final boolean requiresEncounter = scope.contains("launch/encounter");
        final boolean hasEncounter = StringUtils.hasText(launchContext.getEncounter());
        if (requiresEncounter && !hasEncounter) {
            return Optional.of(smartAuthProperties.getContextInitializers().getEncounter());
        }

        final boolean requiresResource = scope.contains("launch/resource");
        final boolean hasResource = StringUtils.hasText(launchContext.getResource());
        if (requiresResource && !hasResource) {
            return Optional.of(smartAuthProperties.getContextInitializers().getResource());
        }

        return Optional.empty();
    }
}
