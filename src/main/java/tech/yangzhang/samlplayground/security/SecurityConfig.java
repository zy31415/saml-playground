package tech.yangzhang.samlplayground.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Basic route auth rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/error", "/saml2/**", "/login/**").permitAll()
                        .anyRequest().authenticated()
                )

                // Enable SAML2 login
                .saml2Login(Customizer.withDefaults())

                // Enable SAML2 logout (we'll make it "real" SLO later)
                .saml2Logout(Customizer.withDefaults())

        // For local experimentation, keep CSRF enabled by default.
        // If you later build custom POST endpoints, revisit CSRF.
        ;

        return http.build();
    }
}