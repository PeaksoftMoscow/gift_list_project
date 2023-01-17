package com.peaksoft.repository;

import com.peaksoft.entityUser.UserG;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<UserG, String> {
}
