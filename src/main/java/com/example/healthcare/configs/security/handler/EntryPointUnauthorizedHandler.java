package com.example.healthcare.configs.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        final String expired = (String) httpServletRequest.getAttribute("expired");
        System.out.println(expired);
        if (expired != null) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
        }

    }
}
