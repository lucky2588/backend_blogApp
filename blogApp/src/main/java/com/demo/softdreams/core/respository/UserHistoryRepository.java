package com.demo.softdreams.core.respository;

import com.demo.softdreams.core.entites.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}