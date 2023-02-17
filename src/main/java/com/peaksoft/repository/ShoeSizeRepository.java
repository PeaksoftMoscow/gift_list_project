package com.peaksoft.repository;

import com.peaksoft.model.entity.ShoeSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoeSizeRepository extends JpaRepository<ShoeSize,Long> {

    @Query("SELECT s FROM ShoeSize  s where  s.size = 38")
    List<ShoeSize> getDefaultSize();

}
