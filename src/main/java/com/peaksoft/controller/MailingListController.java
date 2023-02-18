package com.peaksoft.controller;

import com.peaksoft.dto.MailingListResponse;
import com.peaksoft.service.MailingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/mailing_list")
public class MailingListController {

    private final MailingListService mailingListService;

    public MailingListResponse sendAndSave(){
        return mailingListService.send();
    }
}
