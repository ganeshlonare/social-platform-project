package com.project.socialPlatform.userService.repository;

import com.project.socialPlatform.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean findByEmail(String email);

    boolean existsByEmail(String email);
}
