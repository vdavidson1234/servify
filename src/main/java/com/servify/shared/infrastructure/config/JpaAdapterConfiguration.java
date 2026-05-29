package com.servify.shared.infrastructure.config;

import com.servify.autenticacion.application.port.out.PasswordHasherPort;
import com.servify.autenticacion.application.port.out.TokenProviderPort;
import com.servify.autenticacion.application.dto.TokenResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Configuración de beans que no dependen de persistencia:
 * PasswordHasher y TokenProvider.
 * Los adapters JPA se registran solos via @Component.
 */
@Configuration
public class JpaAdapterConfiguration {

    @Bean
    public PasswordHasherPort passwordHasherPort() {
        return new Sha256PasswordHasher();
    }

    @Bean
    public TokenProviderPort tokenProviderPort() {
        return new SimpleTokenProvider();
    }

    private static class Sha256PasswordHasher implements PasswordHasherPort {
        @Override
        public String hashear(String passwordPlano) {
            return "sha256:" + sha256(passwordPlano == null ? "" : passwordPlano);
        }

        @Override
        public boolean coincide(String passwordPlano, String passwordHash) {
            return Objects.equals(hashear(passwordPlano), passwordHash);
        }
    }

    private static class SimpleTokenProvider implements TokenProviderPort {
        @Override
        public TokenResult generarAccessToken(UUID usuarioId, String emailAcceso) {
            LocalDateTime ahora = LocalDateTime.now();
            return new TokenResult(
                    "access-" + usuarioId + "-" + UUID.randomUUID(),
                    "Bearer", ahora, ahora.plusMinutes(30));
        }

        @Override
        public TokenResult generarRefreshToken(UUID usuarioId, String emailAcceso) {
            LocalDateTime ahora = LocalDateTime.now();
            return new TokenResult(
                    "refresh-" + usuarioId + "-" + UUID.randomUUID(),
                    "Bearer", ahora, ahora.plusDays(7));
        }

        @Override
        public String obtenerHashToken(String token) {
            return sha256(token == null ? "" : token);
        }
    }

    static String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo calcular SHA-256", e);
        }
    }
}
