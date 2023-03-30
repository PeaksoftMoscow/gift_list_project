package com.peaksoft.repository;

import com.peaksoft.model.entity.Charity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharityRepository extends JpaRepository<Charity,Long> {

    @Query("SELECT s FROM Charity s")
    List<Charity> getByPagination(PageRequest pageRequest);


    @Query("SELECT s FROM Charity s WHERE UPPER(s.charityName) LIKE concat(' % ', :text, ' %')" +
            "OR UPPER(s.charityName) LIKE concat(' %', :text, '%') OR UPPER(s.description) LIKE concat('%', :text, '%')")
    List<Charity> searchAndPagination(@Param("text") String text, PageRequest pageable);

}
