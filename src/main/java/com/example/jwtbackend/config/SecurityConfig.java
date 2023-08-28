package com.example.jwtbackend.config;

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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@ComponentScan
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {




    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    private final UserAuthenticationProvider userAuthenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
//        httpBasic(Customizer.withDefaults())
//                .
        exceptionHandling((exceptionHandling) ->
                exceptionHandling.authenticationEntryPoint(userAuthenticationEntryPoint))
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                        .requestMatchers("/products**","/products/**","/create","/myproducts/**").permitAll()
                        .requestMatchers("/create").hasAnyRole("ADMIN","SELLER")
                        .anyRequest().authenticated())
                .oauth2Login()
                .authorizationEndpoint()
        ;
        return http.build();
    }
}