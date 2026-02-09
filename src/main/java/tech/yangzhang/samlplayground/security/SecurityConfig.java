package tech.yangzhang.samlplayground.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, Environment env) throws Exception {
        boolean samlEnabled = env.containsProperty("IDP_METADATA_URI")
                && env.getProperty("IDP_METADATA_URI") != null
                && !env.getProperty("IDP_METADATA_URI").isBlank();

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**", "/error").permitAll()
                .anyRequest().authenticated()
        );

        if (samlEnabled) {
            http.saml2Login(Customizer.withDefaults())
                    .saml2Logout(Customizer.withDefaults());
        } else {
            // Boot-friendly fallback: show 401 on /dashboard instead of crashing at startup
            http.httpBasic(Customizer.withDefaults());
        }

        return http.build();
    }
}