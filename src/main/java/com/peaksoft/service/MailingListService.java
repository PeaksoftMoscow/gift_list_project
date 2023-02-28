package com.peaksoft.service;

import com.peaksoft.dto.MailingListRequest;
import com.peaksoft.dto.MailingListResponse;
import com.peaksoft.exception.EmtyValueException;
import com.peaksoft.mapper.MailingListMapper;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.MailingList;
import com.peaksoft.repository.MailingListRepository;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailingListService {

    private final MailingListRepository mailingListRepository;
    private final JavaMailSender javaMailSender;
    private final MailingListMapper mailingListMapper;
    private final UserRepository userRepository;

    public MailingListResponse send(MailingListRequest mailingListRequest) {
        List<User> users = userRepository.findAll();
        for (User user : users){
            if (user.isSubscribeToNewsletter()){
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("ssaidibakas@gmail.com");
                simpleMailMessage.setTo(user.getEmail());
                simpleMailMessage.setSubject(mailingListRequest.getHeader());
                simpleMailMessage.setText(mailingListRequest.getText());
                this.javaMailSender.send(simpleMailMessage);
            }
        }
        return mailingListMapper.mapToResponse(mailingListRepository.save(mailingListMapper.mapToEntity(mailingListRequest)));

    }

    public List<MailingListResponse> getAllMailingList(){
        List<MailingList> mailingLists = mailingListRepository.findAll();
        if (mailingLists.isEmpty()){
            throw new EmtyValueException("MailingList are empty");
        }
        return view(mailingLists);
    }

    public MailingListResponse getMailingById(Long id){
        MailingList mailingList = mailingListRepository.findById(id).orElseThrow(() ->
                new EmtyValueException("Mailing list is empty"));
        return mailingListMapper.mapToResponse(mailingList);
    }


    private List<MailingListResponse> view(List<MailingList> mailingLists) {
        List<MailingListResponse> responses = new ArrayList<>();
        for (MailingList mailing : mailingLists){
            responses.add(mailingListMapper.mapToResponse(mailing));
        }
        return responses;
    }
}
