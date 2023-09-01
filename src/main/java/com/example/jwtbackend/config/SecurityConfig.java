package com.example.jwtbackend.config;

import com.example.jwtbackend.exception.CustomAuthenticationSuccessHandler;
import com.example.jwtbackend.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@ComponentScan
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final CustomOAuth2UserService customOauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(userAuthenticationEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        .requestMatchers("/products**", "/products/**", "/create", "/",
                                "/myproducts/**").permitAll()
                        .requestMatchers("/public/**", "/auth/**", "/oauth2/**").permitAll()

                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.
                                userService(customOauth2UserService))
                        .successHandler(customAuthenticationSuccessHandler))
                .logout(l -> l.logoutSuccessUrl("/").permitAll());
        return http.build();
    }

}
