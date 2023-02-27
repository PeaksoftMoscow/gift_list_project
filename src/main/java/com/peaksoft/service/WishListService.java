package com.peaksoft.service;

import com.peaksoft.dto.WishListRequest;
import com.peaksoft.dto.WishListResponse;
import com.peaksoft.halidayCode.repository.HolidayRepository;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.Holiday;
import com.peaksoft.model.entity.WishList;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {


    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;

    private final HolidayRepository holidayRepository;

    public WishListResponse create(WishListRequest request) {
        User user = new User();
        Holiday holiday = findByIdHoliday(request.getHolidayId());
        WishList wishList = saveMethod(request);
        wishList.setUser(user);
        wishList.setHolidays(holiday);
        wishList.setHolidayId(request.getHolidayId());
        holiday.setWishList(wishList.getHolidays().getWishList());
        if (user.isBlocked()) {
            throw new NotFoundException("Your account is blocked ");
        }
        wishListRepository.save(wishList);
        return mapToResponse(wishList);
    }


    public WishListResponse update(WishListRequest request, Long id) {
        WishList wishList = findByIdWish(id);
        wishList.setGiftName(request.getGiftName());
        wishList.setLink(request.getLink());
        wishList.setDescription(request.getDescription());
        wishList.setImage(request.getImage());
        wishList.setHolidayId(request.getHolidayId());
        wishList.setHolidayDate(request.getHolidayDate());
        wishListRepository.save(wishList);
        return wishListResponse(wishList);
    }


    public void deleteById(Long id) {
        WishList wishList = findByIdWish(id);
        wishListRepository.delete(wishList);
    }

    public WishList findByIdWish(Long id) {
        return wishListRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("WishList with id not found", id)));
    }

    public List<WishList> getAll() {
        return wishListRepository.findAll();
    }


    public WishList saveMethod(WishListRequest request) {
        WishList wishList = new WishList();
        wishList.setGiftName(request.getGiftName());
        wishList.setLink(request.getLink());
        wishList.setUser(request.getUser());
        wishList.setCreated(LocalDate.now());
        wishList.setImage(request.getImage());
        wishList.setHolidayId(request.getHolidayId());
        wishList.setDescription(request.getDescription());
        wishList.setHolidayDate(request.getHolidayDate());
        return wishList;
    }

    public WishListResponse wishListResponse(WishList wishList) {
        return WishListResponse.builder()
                .id(wishList.getId())
                .link(wishList.getLink())
                .holidayDate(wishList.getHolidayDate())
                .description(wishList.getDescription())
                .image(wishList.getImage())
                .giftName(wishList.getGiftName())
                .holidayId(wishList.getId())
                .build();
    }


    public Holiday findByIdHoliday(Long holidayId) {
        return holidayRepository.findById(holidayId).orElseThrow(()
                -> new NotFoundException(String.format("Holiday with id not found", holidayId)));
    }

    public User getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).get();
    }

    public User findByIUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new NotFoundException(String.format("User with id not found", userId)));
    }


    public WishListResponse mapToResponse(WishList wishList) {
        if (wishList == null) {
            return null;
        }
        WishListResponse wishListResponse = new WishListResponse();
        wishListResponse.setId(wishList.getId());
        wishListResponse.setGiftName(wishList.getGiftName());
        wishListResponse.setDescription(wishList.getDescription());
        wishListResponse.setLink(wishList.getLink());
        wishListResponse.setHolidayId(wishList.getHolidayId());
        wishListResponse.setHolidayDate(wishList.getHolidayDate());
        wishListResponse.setImage(wishList.getImage());
        return wishListResponse;
    }
}
