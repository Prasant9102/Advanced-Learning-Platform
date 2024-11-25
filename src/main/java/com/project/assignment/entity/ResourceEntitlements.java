package com.project.assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resource_entitlements", uniqueConstraints = @UniqueConstraint(columnNames = {"resource_id", "entitlement_id"}))
public class ResourceEntitlements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_entitlement_id")
    private Long resourceEntitlementId;

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id")
    private Resources resource;

    @ManyToOne
    @JoinColumn(name = "entitlement_id", referencedColumnName = "entitlement_id")
    private Entitlements entitlement;

    // Getters and Setters
    public Long getResourceEntitlementId() {
        return resourceEntitlementId;
    }

    public void setResourceEntitlementId(Long resourceEntitlementId) {
        this.resourceEntitlementId = resourceEntitlementId;
    }

    public Resources getResource() {
        return resource;
    }

    public void setResource(Resources resource) {
        this.resource = resource;
    }

    public Entitlements getEntitlement() {
        return entitlement;
    }

    public void setEntitlement(Entitlements entitlement) {
        this.entitlement = entitlement;
    }

    public void setResourceId(int i) {
    }

    public void setEntitlementId(Long resourceEntitlementId) {
    }
}
