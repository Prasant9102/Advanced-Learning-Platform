INSERT INTO project.courses (
    course_name,
    start_date,
    end_date,
    description,
    created_by,
    created_date,
    updated_by,
    updated_date
)
VALUES (
    'Advanced Java Programming',
    '2024-11-01',
    '2024-12-31',
    'This course covers advanced concepts in Java including Spring Framework, Hibernate, and Microservices.',
    'admin_user',
    CURRENT_TIMESTAMP,
    'admin_user',
    CURRENT_TIMESTAMP
)
ON DUPLICATE KEY UPDATE
    course_name = VALUES(course_name),
    start_date = VALUES(start_date),
    end_date = VALUES(end_date),
    description = VALUES(description),
    updated_by = VALUES(updated_by),
    updated_date = VALUES(updated_date);




    -- Insert roles
    INSERT INTO roles (
        role_name, role_description, status, course_id, created_by, created_date, updated_by, updated_date
    ) VALUES
        ('SuperAdmin', 'Full access to all resources and settings.', 'Active', 1, 1, '2024-01-01', 1, '2024-01-01')
    ON DUPLICATE KEY UPDATE
        role_description = VALUES(role_description),
        status = VALUES(status),
        updated_by = VALUES(updated_by),
        updated_date = VALUES(updated_date);

INSERT INTO users (
        user_id, user_name, user_passwd, user_email, first_name, last_name, status, role_id, course_id, last_logged_in_time, created_by, created_date, updated_by, updated_date
    ) VALUES (
        1000, 'superAdmin', '$2b$12$2pnIDXEiyryKq3As8NiF0.5b8eTsXxZEaIFEw3DY4MVlImpwHKs8m', 'superadmin@prasant.com', 'Super', 'Admin', 'ACTIVE', 1, 1, NULL, 1000, CURRENT_DATE, 1000, CURRENT_DATE
    )
    ON DUPLICATE KEY UPDATE
        user_name = VALUES(user_name),
        user_passwd = VALUES(user_passwd),
        user_email = VALUES(user_email),
        first_name = VALUES(first_name),
        last_name = VALUES(last_name),
        status = VALUES(status),
        role_id = VALUES(role_id),
        course_id = VALUES(course_id),
        last_logged_in_time = VALUES(last_logged_in_time),
        created_by = VALUES(created_by),
        created_date = VALUES(created_date),
        updated_by = VALUES(updated_by),
        updated_date = VALUES(updated_date);


        -- Insert resources with duplicate key handling
        INSERT INTO resources (resource_name) VALUES ('UserManagement')
        ON DUPLICATE KEY UPDATE resource_name = VALUES(resource_name);
        INSERT INTO resources (resource_name) VALUES ('RoleManagement')
        ON DUPLICATE KEY UPDATE resource_name = VALUES(resource_name);
        INSERT INTO resources (resource_name) VALUES ('CourseManagement')
        ON DUPLICATE KEY UPDATE resource_name = VALUES(resource_name);
        INSERT INTO resources (resource_name) VALUES ('TechnologiesManagement')
        ON DUPLICATE KEY UPDATE resource_name = VALUES(resource_name);
        INSERT INTO resources (resource_name) VALUES ('TaskAssignedManagement')
        ON DUPLICATE KEY UPDATE resource_name = VALUES(resource_name);


        -- Insert entitlements with duplicate key handling
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.userManagement.user.create')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.userManagement.user.update')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.userManagement.user.view')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.userManagement.user.delete')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.roleManagement.role.create')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.roleManagement.role.update')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.roleManagement.role.view')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.roleManagement.role.delete')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.courseManagement.course.create')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.courseManagement.course.update')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.courseManagement.course.view')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.courseManagement.course.delete')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.technologiesManagement.technologies.create')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.technologiesManagement.technologies.update')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.technologiesManagement.technologies.view')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.technologiesManagement.technologies.delete')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.taskAssignManagement.task.create')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.taskAssignManagement.task.update')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.taskAssignManagement.task.view')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);
        INSERT INTO entitlements (entitlement_name) VALUES ('alp.taskAssignManagement.task.delete')
        ON DUPLICATE KEY UPDATE entitlement_name = VALUES(entitlement_name);

        -- Insert resource entitlements for UserAdministration (resource_id 1) with duplicate key handling
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (1, 1)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (1, 2)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (1, 3)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (1, 4)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (2, 5)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (2, 6)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (2, 7)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (2, 8)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (3, 9)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (3, 10)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (3, 11)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (3, 12)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (4, 13)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (4, 14)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (4, 15)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (4, 16)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (5, 17)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (5, 18)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (5, 19)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);
        INSERT INTO resource_entitlements (resource_id, entitlement_id) VALUES (5, 20)
        ON DUPLICATE KEY UPDATE resource_id = VALUES(resource_id), entitlement_id = VALUES(entitlement_id);


        -- Insert role resource entitlements for role_id 1 with duplicate key handling
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 1)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 2)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 3)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 4)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 5)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 6)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 7)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 8)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 9)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 10)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 11)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 12)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 13)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 14)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 15)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 16)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 17)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 18)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 19)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);
        INSERT INTO role_resource_entitlement (role_id, resource_entitlement_id) VALUES (1, 20)
        ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), resource_entitlement_id = VALUES(resource_entitlement_id);