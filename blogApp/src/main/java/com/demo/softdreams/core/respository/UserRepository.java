package com.demo.softdreams.core.respository;

import com.demo.softdreams.core.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User>  findByMsisdn(String msisdn);

    User findByIdAndIsEnableTrueAndDeletedFalse(Long id);



    @Override
    Optional<User> findById(Long aLong);
}