package com.olafenko.taskman.repositories;

import com.olafenko.taskman.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {


    Optional<AppUser> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<AppUser> findByUsername(String username);

    Boolean existsByUsername(String username);

}
