package gov.samhsa.ocp.smartcore.service;

import java.net.URI;

public interface LauncherService {
    URI getLaunchRedirectUri(String clientId);
}
