package com.peaksoft.service;

import com.peaksoft.dto.FriendResponse;
import com.peaksoft.exception.NotFoundException;
import com.peaksoft.mapper.FriendVIewMapper;
import com.peaksoft.model.User;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final UserRepository userRepository;
    private final FriendVIewMapper friendVIewMapper;

    public User findByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new NotFoundException(String.format("User with id not founds", userId)));
    }

    public List<FriendResponse> getAll() {
        User friend = getAuthenticatedUser();
        return friendVIewMapper.getAllFriends(userRepository.findAllFriends(friend.getId()));
    }

    public List<FriendResponse> getAllRequest() {
        User friend = getAuthenticatedUser();
        return friendVIewMapper.getAllFriends(userRepository.findAllRequest(friend.getId()));
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found!"));
    }

    public FriendResponse requestToFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (friend.getRequestToFriends().contains(user)) {
            if (friend.equals(user)) {
                throw new NotFoundException("Friend with id not found " + friend.getId());
            }
            if (!(user.getRequestToFriends().contains(friend))) {
                throw new NotFoundException("You're already get request " + friend.getId());
            }
            if ((user.getRequestToFriends().contains(friend))) {
                throw new NotFoundException("You're already get request " + friend.getId());
            }
        }
        if ((friend.getFriends().contains(user))) {
            if (user.getFriends().isEmpty()) {
                throw new NotFoundException("You're already friends " + friend.getId());
            }

        }
        if (!(user.getRequestToFriends().contains(friend))) {
            if (user.getId().equals(friend.getId())) {
                throw new NotFoundException("You can't follow you " + friend.getId());
            }
        }
        if (user.getRequestToFriends().contains(friend)) {
            if (!friend.getRequestToFriends().contains(user)) {
                throw new NotFoundException("You can request after cancel or accept");
            }
        }

        friend.addRequestToFriend(user);
        userRepository.save(friend);
        return friendResponse(friend);

    }

    public FriendResponse cancelRequestToFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (user.getRequestToFriends().contains(friend)) {
            user.getRequestToFriends().remove(friend);
            userRepository.save(user);
            System.out.println("Success cancelled ");
        } else {
            throw new NotFoundException("Friend with id not found " + friend.getId());
        }

        return friendResponse(friend);
    }

    public FriendResponse acceptToFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (!(user.getRequestToFriends().contains(friend))) {
            throw new NotFoundException("You're already friends " + friend.getId());
        } else {
            user.acceptToFriend(friend);
            user.getRequestToFriends().remove(friend);
            user.getRequestToFriends().remove(friend);
            userRepository.save(friend);
        }
        return friendResponse(friend);
    }

    public FriendResponse deleteFriend(Long friendId) {
        User user = getAuthenticatedUser();
        User friend = findByUserId(friendId);
        if (user.getFriends().contains(friend)) {
            user.getFriends().remove(friend);
            user.addRequestToFriend(friend);
            userRepository.save(friend);
        } else {
            throw new NotFoundException("Friend with id not found " + friend.getId());
        }
        return friendResponse(friend);
    }


    public FriendResponse friendResponse(User user) {
        return FriendResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
