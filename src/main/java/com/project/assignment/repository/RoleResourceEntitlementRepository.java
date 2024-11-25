package com.project.assignment.repository;

import com.project.assignment.entity.ResourceEntitlements;
import com.project.assignment.entity.RoleResourceEntitlements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleResourceEntitlementRepository extends JpaRepository<RoleResourceEntitlements, Long> {

    @Query("SELECT rre.resourceEntitlement.entitlement.entitlementName, rre.resourceEntitlement.resource.resourceName FROM RoleResourceEntitlements rre WHERE rre.roleId = :roleId")
    List<Object[]> findEntitlementNamesAndResourceNamesByRoleId(Long roleId);

    Optional<RoleResourceEntitlements> findByRoleIdAndResourceEntitlement(Long roleId, ResourceEntitlements resourceEntitlement);

    List<RoleResourceEntitlements> findByRoleId(Long roleId);
}
