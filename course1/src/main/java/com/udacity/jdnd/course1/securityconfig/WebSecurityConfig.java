package com.udacity.jdnd.course1.securityconfig;

import com.udacity.jdnd.course1.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private AuthenticationService authenticationService;

    public WebSecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login","/signup","/h2-console/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationService)
             .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/chat")
                .permitAll()
             )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")  // Ensure it redirects to a valid page after logout
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .csrf(csrf -> csrf.disable()); // Allow frames for H2 console;

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/favicon.ico", "/resources/**", "/error");
    }
}