package com.peaksoft.mapper;

import com.peaksoft.dto.FriendResponse;
import com.peaksoft.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FriendVIewMapper {

    public FriendResponse viewFriendProfile(User newUser) {
        FriendResponse friendResponse = new FriendResponse();
        friendResponse.setId(newUser.getId());
        friendResponse.setFirstName(newUser.getFirstName());
        friendResponse.setLastName(newUser.getLastName());
        friendResponse.setEmail(newUser.getEmail());
        return friendResponse;
    }

    public List<FriendResponse> getAllFriends(List<User> users) {
        List<FriendResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(viewFriendProfile(user));
        }
        return responses;
    }
}
