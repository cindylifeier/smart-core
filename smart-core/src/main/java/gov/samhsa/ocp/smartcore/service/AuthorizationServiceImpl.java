package gov.samhsa.ocp.smartcore.service;

import gov.samhsa.ocp.smartcore.config.SmartCoreProperties;
import gov.samhsa.ocp.smartcore.domain.Context;
import gov.samhsa.ocp.smartcore.domain.Launch;
import gov.samhsa.ocp.smartcore.domain.LaunchRepository;
import gov.samhsa.ocp.smartcore.config.Constants;
import gov.samhsa.ocp.smartcore.domain.ResponseType;
import gov.samhsa.ocp.smartcore.service.exception.InvalidOrExpiredLaunchIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static gov.samhsa.ocp.smartcore.service.URIUtils.addParams;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    public static final String SPACE = " ";
    public static final String COMMA = ",";

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private SmartCoreProperties smartCoreProperties;

    @Override
    public URI getRedirectUri(String clientId, ResponseType responseType, String scope, String redirectUri, String state, String aud, String launch) {
        return getContextInitializerRedirectUri(clientId, responseType, scope, redirectUri, state, aud, launch)
                .orElseGet(() -> getAuthorizationRedirectUri(clientId, responseType, scope, redirectUri, state, aud, launch));
    }

    private Optional<URI> getContextInitializerRedirectUri(String clientId, ResponseType responseType, String scope, String redirectUri, String state, String aud, String launch) {
        try {
            final List<Context> uninitializedRequiredContexts = getUninitializedRequiredContexts(launch, scope);
            if (!uninitializedRequiredContexts.isEmpty()) {
                final Map<String, String> params = new HashMap<>();
                params.put(Constants.CLIENT_ID, clientId);
                params.put(Constants.RESPONSE_TYPE, responseType.name());
                params.put(Constants.SCOPE, scope);
                params.put(Constants.REDIRECT_URI, redirectUri);
                params.put(Constants.STATE, state);
                params.put(Constants.AUD, aud);
                params.put(Constants.LAUNCH, launch);
                params.put(Constants.REQUIRED_CONTEXT, uninitializedRequiredContexts.stream().map(Context::name).collect(joining(COMMA)));
                final URI baseRedirectUri = new URI(smartCoreProperties.getContextInitializer());
                final URI redirectUriWithParams = addParams(baseRedirectUri, params);
                return Optional.of(redirectUriWithParams);
            }
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return Optional.empty();
    }

    private URI getAuthorizationRedirectUri(String clientId, ResponseType responseType, String scope, String redirectUri, String state, String aud, String launch) {
        try {
            final Map<String, String> params = new HashMap<>();
            params.put(Constants.CLIENT_ID, clientId);
            params.put(Constants.RESPONSE_TYPE, responseType.name());
            params.put(Constants.SCOPE, scope);
            params.put(Constants.REDIRECT_URI, redirectUri);
            params.put(Constants.STATE, state);
            params.put(Constants.AUD, aud);
            params.put(Constants.LAUNCH, launch);
            final URI baseRedirectUri = new URI(smartCoreProperties.getOauth2Authorize());
            final URI redirectUriWithParams = addParams(baseRedirectUri, params);
            return redirectUriWithParams;
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private List<Context> getUninitializedRequiredContexts(String launch, String scope) {
        final List<String> scopes = Arrays.stream(scope.split(SPACE)).filter(StringUtils::hasText).collect(toList());
        final Launch launchContext = launchRepository.findById(launch).orElseThrow(InvalidOrExpiredLaunchIdException::new);

        final List<Context> uninitializedRequiredContexts = scopes.stream()
                .filter(s -> s.startsWith(Context.LAUNCH_SCOPE_PREFIX))
                .map(Context::fromLaunchScope)
                .filter(launchContextRequest -> !StringUtils.hasText(launchContext.getValueByContext(launchContextRequest)))
                .distinct()
                .collect(toList());
        if (!uninitializedRequiredContexts.contains(Context.user) && !StringUtils.hasText(launchContext.getUser())) {
            uninitializedRequiredContexts.add(Context.user);
        }
        return uninitializedRequiredContexts;
    }
}
