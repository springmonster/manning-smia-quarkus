package com.example.i18n;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.Message;

@Localized("es")
public interface SpanishAppMessages extends AppMessages {

    @Override
    @Message("Licencia creada {message}")
    String license_create_message(String message);

    @Override
    @Message("Licencia {message} creada")
    String license_update_message(String message);

    @Override
    @Message("Eliminando licencia con id {licenseId} para la organization {organizationId}")
    String license_delete_message(String licenseId, String organizationId);
}