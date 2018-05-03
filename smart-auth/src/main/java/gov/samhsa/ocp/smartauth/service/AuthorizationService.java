package gov.samhsa.ocp.smartauth.service;

import gov.samhsa.ocp.smartauth.domain.ResponseType;

import java.net.URI;
import java.util.Optional;

public interface AuthorizationService {
    Optional<URI> getContextInitializerRedirectUri(String clientId,
                                                   ResponseType responseType,
                                                   String scope,
                                                   String redirectUri,
                                                   String state,
                                                   String aud,
                                                   String launch);
}
