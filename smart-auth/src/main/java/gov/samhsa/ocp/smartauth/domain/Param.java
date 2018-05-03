package gov.samhsa.ocp.smartauth.domain;

public interface Param {
    // OAuth2
    String CLIENT_ID = "client_id";
    String RESPONSE_TYPE = "response_type";
    String SCOPE = "scope";
    String REDIRECT_URI = "redirect_uri";
    String STATE = "state";
    String AUD = "aud";
    // SMART
    String LAUNCH = "launch";
    // OCP
    String REQUIRED_CONTEXT = "required_context";
}
