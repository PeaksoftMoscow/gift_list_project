package com.peaksoft.repository;

import com.peaksoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);

    @Query("SELECT f FROM User u JOIN u.friends f WHERE u.id=?1")
    List<User> findAllFriends(Long id);

    @Query("SELECT R FROM User u JOIN u.requestToFriends R WHERE u.id=?1")
    List<User> findAllRequest(Long id);


}
