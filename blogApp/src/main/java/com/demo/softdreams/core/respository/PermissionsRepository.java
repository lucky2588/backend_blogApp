package com.demo.softdreams.core.respository;

import com.demo.softdreams.core.entites.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
}