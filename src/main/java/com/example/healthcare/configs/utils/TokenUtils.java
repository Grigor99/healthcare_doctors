package com.example.healthcare.configs.utils;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    private static final String UTYPE = "u-type";
    private static final String AUTH_HEADER = "Authorization";


    public static String getToken(HttpServletRequest request) {

        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public static String getUsernameFromToken(String token, String secret, HttpServletRequest httpServletRequest) {
        String username;
        try {
            username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

        } catch (MalformedJwtException ex) {
            username = null;
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
            username = null;
            httpServletRequest.setAttribute("expired", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            username = null;
            System.out.println("Unsupported JWT exception");
        } catch (IllegalArgumentException ex) {
            System.out.println("Jwt claims string is empty");
            username = null;

        } catch (Exception e) {
            System.out.println("aaaaa");
            username = null;
        }

        return username;
    }


    public static UserType getUserTypeFromToken(String token, String secret) {
        String type;
        UserType userType = null;

        try {
            type = (String) Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("u-type");
            userType = UserType.getByLabel(type);
        } catch (ExpiredJwtException e) {
            System.out.println(" Token expired");
        } catch (Exception e) {
            System.out.println(" Some other exception in JWT parsing ");
        }

        return userType;
    }

    private static Date getExpirationDate(String token, String secret) {
        Date expiration;
        try {
            expiration = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public static String generateToken(UserDetails userDetails, UserType userType, String secret, long expiration) {
        Claims customClaims = Jwts.claims();
        customClaims.put(UTYPE, userType);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        return Jwts.builder().setClaims(customClaims)
                .setSubject(userDetails.getUsername())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public static boolean validateToken(String token, UserDetails userDetails, String secret) {
//        final String username = getUsernameFromToken(token, secret, request);
//        final Date expiration = getExpirationDate(token, secret);
//        return username.equals(userDetails.getUsername()) &&
//                expiration.after(new Date(System.currentTimeMillis()));
        return true;
    }


}
