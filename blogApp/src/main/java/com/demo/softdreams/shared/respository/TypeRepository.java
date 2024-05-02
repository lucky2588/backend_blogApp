package com.demo.softdreams.shared.respository;

import com.demo.softdreams.core.entites.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TypeRepository extends JpaRepository<Type, Long> {
}