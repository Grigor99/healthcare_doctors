package com.example.healthcare.configs.filter;

import com.example.healthcare.configs.AuthToken;
import com.example.healthcare.configs.security.service.DetailsService;
import com.example.healthcare.configs.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private DetailsService detailsService;

    @Value("${security.accesstoken.secret}")
    private String secret;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        doInnerFilter(httpRequest);
        chain.doFilter(request, response);
    }

    private void doInnerFilter(HttpServletRequest request) throws IOException, ServletException {
        String authToken = TokenUtils.getToken(request);


        try {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                String username = TokenUtils.getUsernameFromToken(authToken, secret, request);
                UserDetails userDetails = detailsService.loadUserByUsername(username);
                if (TokenUtils.validateToken(authToken, userDetails, secret)) {
                    AuthToken authentication = new AuthToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            //@todo log this
        }

    }
}
