package gov.samhsa.ocp.smartauth.service;

import gov.samhsa.ocp.smartauth.config.SmartAuthProperties;
import gov.samhsa.ocp.smartauth.domain.Context;
import gov.samhsa.ocp.smartauth.domain.Launch;
import gov.samhsa.ocp.smartauth.domain.LaunchRepository;
import gov.samhsa.ocp.smartauth.domain.Param;
import gov.samhsa.ocp.smartauth.domain.ResponseType;
import gov.samhsa.ocp.smartauth.service.exception.InvalidOrExpiredLaunchIdException;
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

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {

    public static final String SPACE = " ";
    public static final String COMMA = ",";
    public static final String EQUAL = "=";
    public static final String AMPERSAND = "&";

    @Autowired
    private LaunchRepository launchRepository;

    @Autowired
    private SmartAuthProperties smartAuthProperties;

    private static URI addParams(URI base, Map<String, String> params) throws URISyntaxException {
        final String paramsQueryString = params.entrySet().stream()
                .filter(entry -> StringUtils.hasText(entry.getKey()) && StringUtils.hasText(entry.getValue()))
                .map(entry -> entry.getKey() + EQUAL + entry.getValue())
                .collect(joining(AMPERSAND));
        return new URI(base.getScheme(), base.getAuthority(), base.getPath(), base.getQuery() == null ? paramsQueryString : base.getQuery() + AMPERSAND + paramsQueryString, base.getFragment());
    }

    @Override
    public Optional<URI> getContextInitializerRedirectUri(String clientId, ResponseType responseType, String scope, String redirectUri, String state, String aud, String launch) {
        try {
            final List<Context> uninitializedRequiredContexts = getUninitializedRequiredContexts(launch, scope);
            if (!uninitializedRequiredContexts.isEmpty()) {
                final Map<String, String> params = new HashMap<>();
                params.put(Param.CLIENT_ID, clientId);
                params.put(Param.RESPONSE_TYPE, responseType.name());
                params.put(Param.SCOPE, scope);
                params.put(Param.REDIRECT_URI, redirectUri);
                params.put(Param.STATE, state);
                params.put(Param.AUD, aud);
                params.put(Param.LAUNCH, launch);
                params.put(Param.REQUIRED_CONTEXT, uninitializedRequiredContexts.stream().map(Context::name).collect(joining(COMMA)));
                final URI baseRedirectUri = new URI(smartAuthProperties.getContextInitializer());
                final URI redirectUriWithParams = addParams(baseRedirectUri, params);
                return Optional.of(redirectUriWithParams);
            }
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return Optional.empty();
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
        return uninitializedRequiredContexts;
    }
}
