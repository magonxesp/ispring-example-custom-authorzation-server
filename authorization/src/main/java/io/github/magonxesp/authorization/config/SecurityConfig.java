package io.github.magonxesp.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/.well-known/*").permitAll()
                .requestMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated()
        ).csrf().disable()
        .cors().disable();

        return http.build();
    }

}
