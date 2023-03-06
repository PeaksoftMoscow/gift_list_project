package com.peaksoft.repository;

import com.peaksoft.model.entity.ShoeSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeSizeRepository extends JpaRepository<ShoeSize, Long> {


}
