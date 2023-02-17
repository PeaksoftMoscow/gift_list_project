package com.peaksoft.controller;

import com.peaksoft.dto.MailingListRequest;
import com.peaksoft.dto.MailingListResponse;
import com.peaksoft.service.MailingListService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("api/mailingList")
public class MailingListController {

    private final MailingListService mailingListService;

    @PostMapping("sendAndSave")
    public MailingListResponse sendAndSave(@PathVariable MailingListRequest request){
        return mailingListService.sendAndSave(request);
    }

    @GetMapping("all")
    public List<MailingListResponse> getAllMailingList(){
        return mailingListService.getAllMailingList();
    }

    @GetMapping("id")
    public MailingListResponse getMailingListById(@PathVariable Long id){
        return mailingListService.getMailingListById(id);
    }

}
