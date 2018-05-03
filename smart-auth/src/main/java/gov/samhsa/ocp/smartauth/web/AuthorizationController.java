package gov.samhsa.ocp.smartauth.web;

import gov.samhsa.ocp.smartauth.domain.ResponseType;
import gov.samhsa.ocp.smartauth.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Controller
@Slf4j
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("/authorize")
    public ResponseEntity getAuthorization(
            @RequestParam("client_id") String clientId,
            @RequestParam("response_type") ResponseType responseType,
            @RequestParam("scope") String scopes,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("state") String state,
            @RequestParam("aud") String aud,
            @RequestParam("launch") String launch) throws URISyntaxException {
        log.debug("client_id={}", clientId);
        log.debug("response_type={}", responseType);
        log.debug("scope={}", scopes);
        log.debug("redirect_uri={}", redirectUri);
        log.debug("state={}", state);
        log.debug("aud={}", aud);
        log.debug("launch={}", launch);
        final Optional<String> contextInitializerRedirectUriOptional = authorizationService.getContextInitializerRedirectUri(launch, scopes);
        if (contextInitializerRedirectUriOptional.isPresent()) {
            final String contextInitializerRedirectUri = contextInitializerRedirectUriOptional.get();
            System.out.println(contextInitializerRedirectUri);
            final HttpHeaders headers = new HttpHeaders();
            final URI baseRedirectUri = new URI(contextInitializerRedirectUri);
            final URI redirectUriWithLaunchQueryParam = new URI(baseRedirectUri.getScheme(), baseRedirectUri.getAuthority(), baseRedirectUri.getPath(), "launch=" + launch, baseRedirectUri.getFragment());
            headers.setLocation(redirectUriWithLaunchQueryParam);
            return new ResponseEntity(headers, HttpStatus.FOUND);
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }
}
