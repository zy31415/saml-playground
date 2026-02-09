package tech.yangzhang.samlplayground.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * If IDP_METADATA_URI is empty, we don't create the relying party registration,
 * and the app can still boot for local dev.
 */
@Configuration
@ConditionalOnProperty(name = "IDP_METADATA_URI")
public class SamlConditionalConfig {
    // Intentionally empty: the condition gates the auto-config by requiring the property.
}