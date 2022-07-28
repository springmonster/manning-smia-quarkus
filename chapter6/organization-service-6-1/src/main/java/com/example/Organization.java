package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonSerialize
@Entity
@Table(name = "organizations")
public class Organization extends PanacheEntityBase {
    @Id
    @Column(name = "organization_id", nullable = false)
    @JsonProperty("id")
    String id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    String name;

    @Column(name = "contact_name", nullable = false)
    @JsonProperty("contactName")
    String contactName;

    @Column(name = "contact_email", nullable = false)
    @JsonProperty("contactEmail")
    String contactEmail;

    @Column(name = "contact_phone", nullable = false)
    @JsonProperty("contactPhone")
    String contactPhone;

    public Organization(String id, String name, String contactName, String contactEmail, String contactPhone) {
        this.id = id;
        this.name = name;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Organization() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                '}';
    }
}
