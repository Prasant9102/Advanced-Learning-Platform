package com.project.assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "entitlements", uniqueConstraints = @UniqueConstraint(columnNames = "entitlement_name"))
public class Entitlements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entitlement_id")
    private Long entitlementId;

    @Column(name = "entitlement_name", unique = true)
    private String entitlementName;

    public Long getEntitlementId() {
        return entitlementId;
    }

    public void setEntitlementId(Long entitlementId) {
        this.entitlementId = entitlementId;
    }

    public String getEntitlementName() {
        return entitlementName;
    }

    public void setEntitlementName(String entitlementName) {
        this.entitlementName = entitlementName;
    }
}
