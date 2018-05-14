package gov.samhsa.ocp.smartauth.service;

import gov.samhsa.ocp.smartauth.domain.ResponseType;

import java.net.URI;

public interface AuthorizationService {
    URI getRedirectUri(String clientId,
                       ResponseType responseType,
                       String scope,
                       String redirectUri,
                       String state,
                       String aud,
                       String launch);
}
