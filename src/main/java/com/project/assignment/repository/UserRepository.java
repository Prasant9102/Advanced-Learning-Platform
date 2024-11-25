package com.project.assignment.repository;

import com.project.assignment.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.lastLoggedInTime = :lastLoggedInTime WHERE u.userName = :userName")
    void updateLastLoggedInTime(@Param("userName") String userName, @Param("lastLoggedInTime") LocalDateTime lastLoggedInTime);

    Optional<Users> findByUserName(String userName);

    Optional<Users> findByEmail(String email);

    Users findByResetToken(String resetToken);

    List<Users> findByCourse_CourseIdAndStatus(Long courseId, String status);
}
