package com.peaksoft.repository;

import com.peaksoft.entity.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityRepository extends JpaRepository<Charity,Long> {
}
