package com.demo.softdreams.core.respository;

import com.demo.softdreams.core.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Long> {



    Optional<Role> findByRoleName(String roleName);


}