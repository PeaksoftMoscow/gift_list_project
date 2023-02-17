package com.peaksoft.repository;

import com.peaksoft.model.entity.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingListRepository extends JpaRepository<MailingList ,Long> {
}
