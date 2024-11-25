package com.project.assignment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role_resource_entitlement", uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "resource_entitlement_id"}))
public class RoleResourceEntitlements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_resource_entitlement_id")
    private Long roleResourceEntitlementId;

    @Column(name = "role_id")
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "resource_entitlement_id", referencedColumnName = "resource_entitlement_id")
    private ResourceEntitlements resourceEntitlement;

    // Getters and Setters
    public Long getRoleResourceEntitlementId() {
        return roleResourceEntitlementId;
    }

    public void setRoleResourceEntitlementId(Long roleResourceEntitlementId) {
        this.roleResourceEntitlementId = roleResourceEntitlementId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public ResourceEntitlements getResourceEntitlement() {
        return resourceEntitlement;
    }

    public void setResourceEntitlement(ResourceEntitlements resourceEntitlement) {
        this.resourceEntitlement = resourceEntitlement;
    }
}
