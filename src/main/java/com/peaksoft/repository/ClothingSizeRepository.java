package com.peaksoft.repository;

import com.peaksoft.model.entity.ClothingSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingSizeRepository extends JpaRepository<ClothingSize, Long> {


}