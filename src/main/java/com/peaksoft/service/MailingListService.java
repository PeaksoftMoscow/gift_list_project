package com.peaksoft.service;

import com.peaksoft.dto.MailingListMapper;
import com.peaksoft.dto.MailingListRequest;
import com.peaksoft.dto.MailingListResponse;
import com.peaksoft.exception.EmptyValueException;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.MailingList;
import com.peaksoft.repository.MailingListRepository;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class MailingListService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final MailingListRepository mailingListRepository;
    private final MailingListMapper mailingListMapper;


    public MailingListResponse sendAndSave(MailingListRequest request) {
        List<User> users = userRepository.findAll();
        for (User user : users){
            if (user.isSubscribeToNewsLetter()){
                SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
                simpleMailMessage.setFrom("ssaidibakas@gmail.com");
                simpleMailMessage.setTo(user.getEmail());
                simpleMailMessage.setSubject(request.getHeader());
                simpleMailMessage.setText(request.getText());
                this.javaMailSender.send(simpleMailMessage);
            }
        }
        return mailingListMapper.mapToResponse(mailingListRepository.save(mailingListMapper.mapToEntity(request)));

    }

    public List<MailingListResponse> getAllMailingList(){
        List<MailingList> mailingLists = mailingListRepository.findAll();
        return view(mailingLists);

    }

    public MailingListResponse getMailingListById(Long id){
        MailingList mailingList = mailingListRepository.findById(id).orElseThrow(() ->
           new EmptyValueException("Mailing list is empty"));

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
