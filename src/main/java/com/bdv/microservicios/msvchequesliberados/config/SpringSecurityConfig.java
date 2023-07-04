package com.bdv.microservicios.msvchequesliberados.config;


import com.bdv.microservicios.msvchequesliberados.security.JwtRequestFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.security.config.Customizer.withDefaults;



@Configuration
public class SpringSecurityConfig {



    @Bean
    SecurityFilterChain web(HttpSecurity http,JwtRequestFilter jwtRequestFilter) throws Exception {
        http

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/app/cheques/authenticate").permitAll()
                        .requestMatchers("/app/cheques/consultar/**").hasAnyAuthority("admin","user")
                        .requestMatchers("/app/cheques/modificar/**").hasAuthority("admin")
                        .anyRequest().authenticated()
                )
                .cors(withDefaults())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration
                                                        authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }





}
