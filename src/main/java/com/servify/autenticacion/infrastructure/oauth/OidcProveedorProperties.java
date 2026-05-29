package com.servify.autenticacion.infrastructure.oauth;

import com.servify.autenticacion.domain.enumtype.ProveedorIdentidadExterna;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "servify.auth.external")
public class OidcProveedorProperties {

    private Provider google = Provider.google();
    private Provider linkedin = Provider.linkedin();

    public Provider getGoogle() {
        return google;
    }

    public void setGoogle(Provider google) {
        this.google = google;
    }

    public Provider getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(Provider linkedin) {
        this.linkedin = linkedin;
    }

    public Provider obtener(ProveedorIdentidadExterna proveedor) {
        return switch (proveedor) {
            case GOOGLE -> google;
            case LINKEDIN -> linkedin;
        };
    }

    public static class Provider {

        private boolean enabled = true;
        private String issuer;
        private List<String> acceptedIssuers = new ArrayList<>();
        private String jwksUri;
        private List<String> clientIds = new ArrayList<>();

        static Provider google() {
            Provider provider = new Provider();
            provider.issuer = "https://accounts.google.com";
            provider.acceptedIssuers = new ArrayList<>(List.of("https://accounts.google.com", "accounts.google.com"));
            provider.jwksUri = "https://www.googleapis.com/oauth2/v3/certs";
            return provider;
        }

        static Provider linkedin() {
            Provider provider = new Provider();
            provider.issuer = "https://www.linkedin.com";
            provider.acceptedIssuers = new ArrayList<>(List.of("https://www.linkedin.com"));
            provider.jwksUri = "https://www.linkedin.com/oauth/openid/jwks";
            return provider;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getIssuer() {
            return issuer;
        }

        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        public List<String> getAcceptedIssuers() {
            return acceptedIssuers;
        }

        public void setAcceptedIssuers(List<String> acceptedIssuers) {
            this.acceptedIssuers = acceptedIssuers;
        }

        public String getJwksUri() {
            return jwksUri;
        }

        public void setJwksUri(String jwksUri) {
            this.jwksUri = jwksUri;
        }

        public List<String> getClientIds() {
            return clientIds;
        }

        public void setClientIds(List<String> clientIds) {
            this.clientIds = clientIds;
        }

        public List<String> clientIdsNormalizados() {
            if (clientIds == null) {
                return List.of();
            }
            return clientIds.stream()
                    .filter(clientId -> clientId != null && !clientId.isBlank())
                    .map(String::trim)
                    .toList();
        }

        public List<String> acceptedIssuersNormalizados() {
            if (acceptedIssuers == null || acceptedIssuers.isEmpty()) {
                return issuer == null || issuer.isBlank() ? List.of() : List.of(issuer.trim());
            }
            return acceptedIssuers.stream()
                    .filter(value -> value != null && !value.isBlank())
                    .map(String::trim)
                    .toList();
        }
    }
}
