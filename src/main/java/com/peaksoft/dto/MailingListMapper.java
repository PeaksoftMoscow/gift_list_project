package com.peaksoft.dto;

import com.peaksoft.exception.EmptyValueException;
import com.peaksoft.model.entity.MailingList;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MailingListMapper {

    public MailingList mapToEntity(MailingListRequest request){
        return MailingList.builder().header(request.getHeader())
                .image(request.getImage())
                .text(request.getText())
                .createdAt(LocalDate.now())
                .build();
    }

    public MailingListResponse mapToResponse(MailingList mailingList){
        if (mailingList == null){
            throw new EmptyValueException("Mailing list is empty");
        }
        return MailingListResponse.builder()
                .id(mailingList.getId())
                .image(mailingList.getImage())
                .header(mailingList.getHeader())
                .text(mailingList.getText())
                .createdAd(LocalDate.now())
                .build();
    }
}
