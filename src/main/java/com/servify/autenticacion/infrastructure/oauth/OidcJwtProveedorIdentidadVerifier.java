package com.servify.autenticacion.infrastructure.oauth;

import com.servify.autenticacion.application.dto.AutenticarConIdentidadExternaCommand;
import com.servify.autenticacion.application.dto.IdentidadExternaVerificadaResult;
import com.servify.autenticacion.application.port.out.ProveedorIdentidadVerifierPort;
import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import com.servify.shared.domain.exception.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

class OidcJwtProveedorIdentidadVerifier implements ProveedorIdentidadVerifierPort {

    private final OidcProveedorProperties properties;
    private final Map<ProveedorIdentidadExterna, JwtDecoder> decoders = new ConcurrentHashMap<>();

    OidcJwtProveedorIdentidadVerifier(OidcProveedorProperties properties) {
        this.properties = properties;
    }

    @Override
    public IdentidadExternaVerificadaResult verificar(AutenticarConIdentidadExternaCommand command) {
        validarCommand(command);
        OidcProveedorProperties.Provider provider = properties.obtener(command.getProveedor());
        validarProvider(command.getProveedor(), provider);

        Jwt jwt;
        try {
            jwt = decoders.computeIfAbsent(command.getProveedor(), proveedor -> construirDecoder(provider))
                    .decode(command.getIdToken());
        } catch (JwtException exception) {
            throw new ValidationException("idToken OIDC invalido o expirado: "
                    + describirErrorJwt(exception), exception);
        }
        validarNonce(command, jwt);

        return new IdentidadExternaVerificadaResult(
                command.getProveedor(),
                jwt.getIssuer() != null ? jwt.getIssuer().toString() : null,
                jwt.getAudience(),
                claimString(jwt, "sub"),
                claimString(jwt, "email"),
                claimBoolean(jwt, "email_verified"),
                obtenerNombreMostrado(jwt),
                claimString(jwt, "picture"),
                jwt.getExpiresAt()
        );
    }

    private void validarCommand(AutenticarConIdentidadExternaCommand command) {
        if (command == null) {
            throw new ValidationException("El comando de autenticacion externa es obligatorio");
        }
        if (command.getProveedor() == null) {
            throw new ValidationException("proveedor es obligatorio");
        }
        if (command.getIdToken() == null || command.getIdToken().isBlank()) {
            throw new ValidationException("idToken es obligatorio");
        }
    }

    private void validarProvider(ProveedorIdentidadExterna proveedor,
                                 OidcProveedorProperties.Provider provider) {
        if (provider == null || !provider.isEnabled()) {
            throw new IllegalStateException("Proveedor OIDC deshabilitado: " + proveedor.getApiValue());
        }
        if (provider.getJwksUri() == null || provider.getJwksUri().isBlank()) {
            throw new IllegalStateException("Falta configurar JWKS URI para " + proveedor.getApiValue());
        }
        if (provider.clientIdsNormalizados().isEmpty()) {
            throw new IllegalStateException("Falta configurar client-id OIDC para " + proveedor.getApiValue());
        }
    }

    private JwtDecoder construirDecoder(OidcProveedorProperties.Provider provider) {
        try {
            NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(provider.getJwksUri()).build();
            OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                    JwtValidators.createDefault(),
                    new IssuerValidator(provider.acceptedIssuersNormalizados()),
                    new AudienceValidator(provider.clientIdsNormalizados())
            );
            decoder.setJwtValidator(validator);
            return decoder;
        } catch (JwtException exception) {
            throw new IllegalStateException("No se pudo inicializar el validador OIDC", exception);
        }
    }

    private void validarNonce(AutenticarConIdentidadExternaCommand command, Jwt jwt) {
        if (command.getNonce() == null || command.getNonce().isBlank()) {
            return;
        }
        String nonceToken = claimString(jwt, "nonce");
        if (!Objects.equals(command.getNonce(), nonceToken)) {
            throw new ValidationException("El nonce del token OIDC no coincide");
        }
    }

    private String obtenerNombreMostrado(Jwt jwt) {
        String name = claimString(jwt, "name");
        if (name != null && !name.isBlank()) {
            return name;
        }
        String givenName = claimString(jwt, "given_name");
        String familyName = claimString(jwt, "family_name");
        String combined = ((givenName == null ? "" : givenName) + " " + (familyName == null ? "" : familyName)).trim();
        return combined.isBlank() ? null : combined;
    }

    private String claimString(Jwt jwt, String claimName) {
        Object value = jwt.getClaims().get(claimName);
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }

    private Boolean claimBoolean(Jwt jwt, String claimName) {
        Object value = jwt.getClaims().get(claimName);
        if (value instanceof Boolean booleanValue) {
            return booleanValue;
        }
        if (value instanceof String stringValue) {
            return Boolean.parseBoolean(stringValue);
        }
        return false;
    }

    private String describirErrorJwt(JwtException exception) {
        if (exception instanceof JwtValidationException validationException) {
            String detalle = validationException.getErrors().stream()
                    .map(OAuth2Error::getDescription)
                    .filter(description -> description != null && !description.isBlank())
                    .collect(Collectors.joining("; "));
            if (!detalle.isBlank()) {
                return detalle;
            }
        }
        String message = exception.getMessage();
        return message == null || message.isBlank()
                ? "no se pudo validar firma, issuer, audience o expiracion"
                : message;
    }

    private static class IssuerValidator implements OAuth2TokenValidator<Jwt> {

        private final List<String> acceptedIssuers;

        private IssuerValidator(List<String> acceptedIssuers) {
            this.acceptedIssuers = acceptedIssuers == null ? List.of() : List.copyOf(acceptedIssuers);
        }

        @Override
        public OAuth2TokenValidatorResult validate(Jwt token) {
            String issuer = token.getIssuer() != null ? token.getIssuer().toString() : null;
            if (issuer != null && acceptedIssuers.contains(issuer)) {
                return OAuth2TokenValidatorResult.success();
            }
            return OAuth2TokenValidatorResult.failure(new OAuth2Error(
                    "invalid_token",
                    "Issuer OIDC no permitido",
                    null
            ));
        }
    }

    private static class AudienceValidator implements OAuth2TokenValidator<Jwt> {

        private final List<String> clientIds;

        private AudienceValidator(List<String> clientIds) {
            this.clientIds = clientIds == null ? List.of() : List.copyOf(clientIds);
        }

        @Override
        public OAuth2TokenValidatorResult validate(Jwt token) {
            if (token.getAudience() != null && token.getAudience().stream().anyMatch(clientIds::contains)) {
                return OAuth2TokenValidatorResult.success();
            }
            return OAuth2TokenValidatorResult.failure(new OAuth2Error(
                    "invalid_token",
                    "Audience OIDC no coincide con client-id configurado",
                    null
            ));
        }
    }
}
