package com.peaksoft.controller;

import com.peaksoft.dto.MailingListRequest;
import com.peaksoft.dto.MailingListResponse;
import com.peaksoft.service.MailingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/mailing_list")
public class MailingListController {

    private final MailingListService mailingListService;

    @PostMapping("send")
    public MailingListResponse sendAndSave(@PathVariable MailingListRequest mailingListRequest){
        return mailingListService.send(mailingListRequest);

    }

    @GetMapping("all")
    public List<MailingListResponse> getAllMailingList(){
        return mailingListService.getAllMailingList();
    }

    @GetMapping("id")
    public MailingListResponse getMailingListById(@PathVariable Long id){
        return mailingListService.getMailingById(id);
    }
}
