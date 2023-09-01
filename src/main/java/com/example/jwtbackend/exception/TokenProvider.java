package com.example.jwtbackend.exception;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwtbackend.service.CustomUserDetails;
import com.example.jwtbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String jwtSecret;

    @Value("${app.jwt.expiration.minutes}")
    private int jwtExpirationMinutes;

    private final UserService userService;

    public TokenProvider(UserService userService) {
        this.userService = userService;
    }

    public String generate(Authentication authentication) {
        OidcUser oidcUser2 = (OidcUser) authentication.getPrincipal();
        System.out.println(oidcUser2.getEmail() + " the name is " + oidcUser2.getName());
        if (authentication.getPrincipal() instanceof OidcUser) {
            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
            System.out.println(((OidcUser) authentication.getPrincipal()).getName());
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

            return JWT.create()
                    .withSubject(oidcUser.getSubject())
                    .withExpiresAt(Date.from(ZonedDateTime.now().plusMinutes(jwtExpirationMinutes).toInstant()))
                    .withIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim("rol", roles)
                    .withClaim("name", oidcUser.getName())
                    .withClaim("preferred_username", oidcUser.getPreferredUsername())
                    .withClaim("email", oidcUser.getEmail())
                    .sign(algorithm);
        } else {
            throw new IllegalArgumentException("Authentication principal is not of type OidcUser.");
        }
    }
    public Optional<DecodedJWT> validateTokenAndGetJws(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT decoded = verifier.verify(token);

            return Optional.of(decoded);
        } catch (Exception exception) {
            log.error("Request to parse JWT failed: {}", exception.getMessage());
        }
        return Optional.empty();
    }

    public static final String TOKEN_TYPE = "JWT";
}