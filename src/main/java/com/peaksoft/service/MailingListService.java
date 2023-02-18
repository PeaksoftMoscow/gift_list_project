package com.peaksoft.service;

import com.peaksoft.dto.MailingListResponse;
import com.peaksoft.repository.MailingListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailingListService {

    private final MailingListRepository mailingListRepository;

    public MailingListResponse send() {

    }
}
