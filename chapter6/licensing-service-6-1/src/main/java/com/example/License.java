package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "License.getByOrganizationIdAndLicenseId",
                query = "from License where organizationId = :organizationId and licenseId = :licenseId")
})
public class License extends PanacheEntityBase {

    @Id
    private String licenseId;
    private String organizationId;
    private String description;
    private String productName;
    private String licenseType;
    private String comment;

    @Transient
    private String organizationName;
    @Transient
    private String contactName;
    @Transient
    private String contactPhone;
    @Transient
    private String contactEmail;

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "License{" +
                "licenseId='" + licenseId + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", description='" + description + '\'' +
                ", productName='" + productName + '\'' +
                ", licenseType='" + licenseType + '\'' +
                ", comment='" + comment + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                '}';
    }

    public static Uni<License> getByOrganizationIdAndLicenseId(String organizationId, String licenseId) {
        return find("#License.getByOrganizationIdAndLicenseId", Parameters.with("organizationId", organizationId).and("licenseId", licenseId)).firstResult();
    }
}
