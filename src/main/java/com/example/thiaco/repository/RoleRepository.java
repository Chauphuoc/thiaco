package com.example.thiaco.repository;

import com.example.thiaco.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

    Optional<Role> findByCode(String code);
}
