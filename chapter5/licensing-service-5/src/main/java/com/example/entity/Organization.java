package com.example.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class Organization extends PanacheEntity {
    @Column(nullable = false, unique = true)
    String organizationId;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String contactName;
    @Column(nullable = false)
    String contactEmail;
    @Column(nullable = false)
    String contactPhone;

}
