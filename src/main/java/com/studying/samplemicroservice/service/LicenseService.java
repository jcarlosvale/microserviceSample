package com.studying.samplemicroservice.service;

import com.studying.samplemicroservice.model.License;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messages;

    public License getLicense(String licenseId, String organizationId){
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale){
        String responseMessage = null;
        if(license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.create.message", null, locale), license);
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId){
        String responseMessage = null;
        if(Objects.nonNull(license)) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage("license.update.message", null, null), license);
        }
        return responseMessage;
    }
    public String deleteLicense(String licenseId, String organizationId){
        String responseMessage = String.format("Deleting license with id %s for the organization %s",licenseId, organizationId);
        return responseMessage;
    }
}