package com.montiel.smartnotes.repository;

import com.montiel.smartnotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

    boolean existsByEmail(String email);

}
