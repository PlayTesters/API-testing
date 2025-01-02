package org.example.utils;

import com.microsoft.playwright.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ApiHelper {
    public static APIRequestContext createApiContext(Playwright playwright, String username, String password) {
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + encodedAuth);
        headers.put("Content-Type", "application/json");

        return playwright.request().newContext(new APIRequest.NewContextOptions().setExtraHTTPHeaders(headers));
    }
}
