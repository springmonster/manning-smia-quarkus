package com.example.entity;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class License extends PanacheEntity {
    @Column(nullable = false, unique = true)
    private String licenseId;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String organizationId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String licenseType;
    @Column
    private String comment;
}