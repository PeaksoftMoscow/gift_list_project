package com.peaksoft.Booking.repository;

import com.peaksoft.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WislistRepository extends JpaRepository<WishList,Long> {
}
