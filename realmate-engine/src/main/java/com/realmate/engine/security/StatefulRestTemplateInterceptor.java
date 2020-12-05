package com.realmate.engine.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.List;

public class StatefulRestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private List<String> cookie;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (cookie != null) {
           cookie.stream().forEach(cookie -> {
               request.getHeaders().add(HttpHeaders.COOKIE, cookie);
           });
        }
        ClientHttpResponse response = execution.execute(request, body);

        if (cookie == null) {
            cookie = response.getHeaders().get(HttpHeaders.SET_COOKIE);
        }
        return response;
    }
}
