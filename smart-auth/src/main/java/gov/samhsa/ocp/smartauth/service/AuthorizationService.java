package gov.samhsa.ocp.smartauth.service;

import java.util.Optional;

public interface AuthorizationService {
    Optional<String> getContextInitializerRedirectUri(String launch, String scopes);
}
