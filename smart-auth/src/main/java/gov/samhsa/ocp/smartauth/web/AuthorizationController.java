package gov.samhsa.ocp.smartauth.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.samhsa.ocp.smartauth.domain.Param;
import gov.samhsa.ocp.smartauth.domain.ResponseType;
import gov.samhsa.ocp.smartauth.service.AuthorizationService;
import gov.samhsa.ocp.smartauth.service.LaunchService;
import gov.samhsa.ocp.smartauth.service.dto.LaunchResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.Optional;

@Controller
@Slf4j
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private LaunchService launchService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/authorize")
    public ResponseEntity<String> getAuthorization(
            @RequestParam(Param.CLIENT_ID) String clientId,
            @RequestParam(Param.RESPONSE_TYPE) ResponseType responseType,
            @RequestParam(Param.SCOPE) String scopes,
            @RequestParam(Param.REDIRECT_URI) String redirectUri,
            @RequestParam(Param.STATE) String state,
            @RequestParam(Param.AUD) String aud,
            @RequestParam(Param.LAUNCH) String launch) throws JsonProcessingException {
        log.debug("{}={}", Param.CLIENT_ID, clientId);
        log.debug("{}={}", Param.RESPONSE_TYPE, responseType);
        log.debug("{}={}", Param.SCOPE, scopes);
        log.debug("{}={}", Param.REDIRECT_URI, redirectUri);
        log.debug("{}={}", Param.STATE, state);
        log.debug("{}={}", Param.AUD, aud);
        log.debug("{}={}", Param.LAUNCH, launch);

        final Optional<URI> contextInitializerRedirectUriOptional = authorizationService.getContextInitializerRedirectUri(clientId, responseType, scopes, redirectUri, state, aud, launch);
        if (contextInitializerRedirectUriOptional.isPresent()) {
            final URI contextInitializerRedirectUri = contextInitializerRedirectUriOptional.get();
            final HttpHeaders headers = new HttpHeaders();
            headers.setLocation(contextInitializerRedirectUri);
            return new ResponseEntity(headers, HttpStatus.FOUND);
        } else {
            final LaunchResponseDto launchResponseDto = launchService.get(launch);
            final String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(launchResponseDto);
            return new ResponseEntity<>("<html><body><h1>No additional context needed</h1><pre>"+s+"</pre></body></html>",HttpStatus.OK);
        }
    }
}
