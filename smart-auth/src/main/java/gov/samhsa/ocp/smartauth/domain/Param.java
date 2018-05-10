package gov.samhsa.ocp.smartauth.domain;

public interface Param {
    // OAuth2 - Get Authorization Code
    String CLIENT_ID = "client_id";
    String RESPONSE_TYPE = "response_type";
    String SCOPE = "scope";
    String REDIRECT_URI = "redirect_uri";
    String STATE = "state";
    String AUD = "aud";
    String GRANT_TYPE = "grant_type";
    String CODE = "code";
    // SMART
    String LAUNCH = "launch";
    // OCP
    String REQUIRED_CONTEXT = "required_context";
}
