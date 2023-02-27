package com.peaksoft.mapper;

import com.peaksoft.dto.MailingListRequest;
import com.peaksoft.dto.MailingListResponse;
import com.peaksoft.exception.EmtyValueException;
import com.peaksoft.model.entity.MailingList;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MailingListMapper {

    public MailingList mapToEntity(MailingListRequest mailingListRequest) {
        return MailingList.builder().header(mailingListRequest.getHeader())
                .image(mailingListRequest.getImage())
                .text(mailingListRequest.getText())
                .createdAt(LocalDate.now())
                .build();

    }


    public MailingListResponse mapToResponse(MailingList mailingList) {
        if (mailingList == null) {
            throw new EmtyValueException("Mailing lists are empty");
        }
        return MailingListResponse.builder()
                .id(mailingList.getId())
                .header(mailingList.getHeader())
                .image(mailingList.getImage())
                .text(mailingList.getText())
                .createdAt(LocalDate.now())
                .build();
    }
}





