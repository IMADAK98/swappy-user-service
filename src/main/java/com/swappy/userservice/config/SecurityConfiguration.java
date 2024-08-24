package com.swappy.userservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz->
                            authz
                                    .requestMatchers("/api/v1/auth/register").permitAll()
                                    .requestMatchers("/api/v1/auth/login").permitAll()
                                    .requestMatchers("/api/v1//auth/**").permitAll()
                                    .requestMatchers("/actuator/**").permitAll()
                                    .anyRequest()
                                    .authenticated()
                )

                .sessionManagement((sessionManagment)->{
                    sessionManagment.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                            .sessionConcurrency((sessionConcurrency)->
//                                    sessionConcurrency
//                                            .maximumSessions(1)
//                                            .expiredUrl("/login?expired")
//                                    );
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }




}
