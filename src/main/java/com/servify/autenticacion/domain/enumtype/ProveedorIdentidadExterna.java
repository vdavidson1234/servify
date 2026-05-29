package com.servify.autenticacion.domain.enumtype;

public enum ProveedorIdentidadExterna {
    GOOGLE("google"),
    LINKEDIN("linkedin");

    private final String apiValue;

    ProveedorIdentidadExterna(String apiValue) {
        this.apiValue = apiValue;
    }

    public String getApiValue() {
        return apiValue;
    }

    public static ProveedorIdentidadExterna desdeApiValue(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("proveedor no puede ser nulo o vacio");
        }
        for (ProveedorIdentidadExterna proveedor : values()) {
            if (proveedor.apiValue.equalsIgnoreCase(value.trim())
                    || proveedor.name().equalsIgnoreCase(value.trim())) {
                return proveedor;
            }
        }
        throw new IllegalArgumentException("Proveedor de identidad no soportado: " + value);
    }
}
