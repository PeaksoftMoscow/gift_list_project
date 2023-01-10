package com.peaksoft.spring_boot.repository;

import com.peaksoft.spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository < User, Long > {

        Optional<User> findByEmail(String email);
}
