package com.example.i18n;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

@MessageBundle
public interface AppMessages {

    @Message("License created {message}")
    String license_create_message(String message);

    @Message("License {message} updated")
    String license_update_message(String message);

    @Message("Deleting license with id {licenseId} for the organization {organizationId}")
    String license_delete_message(String licenseId, String organizationId);
}
