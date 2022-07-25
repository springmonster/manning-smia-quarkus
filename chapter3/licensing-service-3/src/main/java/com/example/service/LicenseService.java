package com.example.service;

import com.example.i18n.AppMessages;
import com.example.model.License;
import io.quarkus.qute.i18n.Localized;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class LicenseService {

    @Localized("en")
    AppMessages enAppMessages;

    @Localized("es")
    AppMessages esAppMessages;

    public License getLicense(String licenseId, String organizationId) {
        License license = new License();

        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");

        return license;
    }

    public String createLicense(License license, String organizationId, String lang) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            if ("es".equals(lang)) {
                responseMessage = esAppMessages.license_create_message(license.toString());
            } else if ("en".equals(lang)) {
                responseMessage = enAppMessages.license_create_message(license.toString());
            }
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId, String lang) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            if ("es".equals(lang)) {
                responseMessage = esAppMessages.license_update_message(license.toString());
            } else if ("en".equals(lang)) {
                responseMessage = enAppMessages.license_update_message(license.toString());
            }
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId, String lang) {
        String responseMessage = null;
        if ("es".equals(lang)) {
            responseMessage = esAppMessages.license_delete_message(licenseId, organizationId);
        } else if ("en".equals(lang)) {
            responseMessage = enAppMessages.license_delete_message(licenseId, organizationId);
        }
        return responseMessage;

    }
}
