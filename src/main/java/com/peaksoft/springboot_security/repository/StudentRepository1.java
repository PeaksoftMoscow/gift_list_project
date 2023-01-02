package com.peaksoft.springboot_security.repository;


import com.peaksoft.springboot_security.entity.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository1 extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE UPPER(s.name) LIKE CONCAT('%',:text,'%')" +
    "OR UPPER(s.surname) LIKE CONCAT('%', :text,'%')"+ "OR UPPER(s.email) LIKE CONCAT('%', :text,'%')")
    List<Student> searchAndPagination(@Param("text") String text, PageRequest pageable);

}