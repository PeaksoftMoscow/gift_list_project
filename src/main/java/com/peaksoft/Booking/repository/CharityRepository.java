package com.peaksoft.Booking.repository;

import com.peaksoft.model.entity.Charity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharityRepository extends JpaRepository<Charity,Long> {
}
