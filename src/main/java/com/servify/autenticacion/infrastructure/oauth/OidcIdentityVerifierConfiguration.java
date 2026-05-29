package com.servify.autenticacion.infrastructure.oauth;

import com.servify.autenticacion.application.port.out.ProveedorIdentidadVerifierPort;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OidcProveedorProperties.class)
public class OidcIdentityVerifierConfiguration {

    @Bean
    @ConditionalOnProperty(name = "servify.auth.external.fake-verifier.enabled", havingValue = "true")
    ProveedorIdentidadVerifierPort fakeProveedorIdentidadVerifierPort() {
        return new FakeProveedorIdentidadVerifier();
    }

    @Bean
    @ConditionalOnMissingBean(ProveedorIdentidadVerifierPort.class)
    ProveedorIdentidadVerifierPort oidcProveedorIdentidadVerifierPort(OidcProveedorProperties properties) {
        return new OidcJwtProveedorIdentidadVerifier(properties);
    }
}
