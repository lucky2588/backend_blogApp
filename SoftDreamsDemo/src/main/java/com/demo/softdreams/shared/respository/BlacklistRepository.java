package com.demo.softdreams.shared.respository;

import com.demo.softdreams.core.entites.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
}