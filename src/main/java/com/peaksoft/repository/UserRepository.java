package com.peaksoft.repository;

import com.peaksoft.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByFirstName(String name);


    @Query("SELECT f FROM User u JOIN u.friends f WHERE u.id=?1")
    List<User> findAllFriends(Long id);

    @Query("SELECT R FROM User u JOIN u.requestToFriends R WHERE u.id=?1")
    List<User> findAllRequest(Long id);

}
