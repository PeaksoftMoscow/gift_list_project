package com.peaksoft.service;

import com.peaksoft.exception.ApiException;
import com.peaksoft.exception.ApiRequestException;
import com.peaksoft.exception.FobidenExceptoin;
import com.peaksoft.exception.NotFoundException;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.Booking;
import com.peaksoft.model.entity.Charity;
import com.peaksoft.model.entity.WishList;
import com.peaksoft.model.entity.enums.CharityStatus;
import com.peaksoft.repository.BookingRepository;
import com.peaksoft.repository.CharityRepository;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BookingServise {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final WishListRepository wislistRepository;
    private final CharityRepository charityRepository;

    public User getPrinciple() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(
                () -> {
                    throw new NotFoundException(String.format("Пользователь с таким электронным адресом: %s не найден!", email));
                });
    }

    public Booking bookCharity(Long chatity_id) {
        User user = getPrinciple();
        Charity charity = charityRepository.findById(chatity_id).orElseThrow(() -> new NotFoundException("Charity not fond"));
        Booking booking = new Booking();
        if (charity.getCharityStatus().equals(CharityStatus.NOT_BOOKED)) {
            booking.setId(booking.getId());
            booking.setCreatedAt(LocalDate.now());
            booking.setCharity(charity);
            booking.setUserId(user);
            bookingRepository.save(booking);
            charity.setCharityStatus(CharityStatus.BOOKED);
            charityRepository.save(charity);
	        return booking;
        }else {
			throw new ApiRequestException("This charity booked");
        }
		
    }

    public String bookWishlist(Long wishlist_id) {
        User user = getPrinciple();
        WishList wishList = wislistRepository.findById(wishlist_id).orElseThrow(() -> new NotFoundException("Wishlist not fond"));
        Booking booking = new Booking();
//	Booking booking = bookingRepository.findById(booking_id).get();
        if (wishList.getCharityStatus().equals(CharityStatus.NOT_BOOKED)) {
            booking.setId(booking.getId());
            booking.setCreatedAt(LocalDate.now());
            booking.setWishList(wishList);
            booking.setUserId(user);
            bookingRepository.save(booking);
            wishList.setCharityStatus(CharityStatus.BOOKED);
            wislistRepository.save(wishList);
			return "Susseckuly WishList booking";
        }else {
			throw new ApiRequestException("This wishlist booked");
        }
       
    }
}
