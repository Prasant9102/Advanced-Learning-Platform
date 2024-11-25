package com.project.assignment.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "resources", uniqueConstraints = @UniqueConstraint(columnNames = "resource_name"))
public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "resource_name", unique = true)
    private String resourceName;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResourceEntitlements> resourceEntitlements;

    // Getters and Setters
    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<ResourceEntitlements> getResourceEntitlements() {
        return resourceEntitlements;
    }

    public void setResourceEntitlements(List<ResourceEntitlements> resourceEntitlements) {
        this.resourceEntitlements = resourceEntitlements;
    }
}
