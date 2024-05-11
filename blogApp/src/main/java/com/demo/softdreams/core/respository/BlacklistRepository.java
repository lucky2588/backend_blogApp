package com.demo.softdreams.core.respository;

import com.demo.softdreams.core.entites.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
}