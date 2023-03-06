package com.peaksoft.api;

import com.peaksoft.dto.FriendResponse;
import com.peaksoft.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendsController {

    private final FriendService friendService;

    @GetMapping("all")
    public List<FriendResponse> getAll() {
        return friendService.getAll();
    }

    @PostMapping("request/{userId}")
    public FriendResponse requestToFriend(@PathVariable Long userId) {
        return friendService.requestToFriend(userId);
    }

    @PostMapping("cancel/{userId}")
    public FriendResponse cancelRequestToFriend(@PathVariable Long userId) {
        return friendService.cancelRequestToFriend(userId);
    }

    @PostMapping("accept/{userId}")
    public FriendResponse acceptToFriend(@PathVariable Long userId) {
        return friendService.acceptToFriend(userId);
    }

    @GetMapping("requests")
    public List<FriendResponse> getAllRequestToFriends() {
        return friendService.getAllRequest();
    }


    @DeleteMapping("delete/{id}")
    public FriendResponse deleteById(@PathVariable Long id) {
        return friendService.deleteFriend(id);
    }
}
