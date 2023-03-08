package com.peaksoft.halidayCode.Servise;

import com.peaksoft.halidayCode.dto.HolidayRequest;
import com.peaksoft.halidayCode.dto.HolidayResponse;
import com.peaksoft.halidayCode.repository.HolidayRepository;

import com.peaksoft.model.User;
import com.peaksoft.model.entity.Holiday;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HolidayServise {
    private final HolidayRepository holidayRepository;
    private final UserRepository userRepository;


    public Holiday create(HolidayRequest holidayRequest, Long userId) {
        User user = userRepository.findById(userId).get();
        holidayRequest.setUser(user);
        Holiday holiday = holidayRepository.save(mapToEntity(holidayRequest));

        return (holiday);

    }


    public HolidayResponse update(Long id, HolidayRequest holidayRequest) {
        Holiday holiday = holidayRepository.findById(id).get();
        holiday.setImage(holidayRequest.getImage());
        holiday.setHolidayName(holidayRequest.getHolidayName());
        holiday.setDate(holidayRequest.getDate());
        holidayRepository.save(holiday);
        return holidayResponse(holiday);
    }

    public HolidayResponse findById(Long id) {
        Holiday holiday = holidayRepository.findById(id).get();
        System.out.println("Get holiday with id: " + id);
        return holidayResponse(holiday);
    }

    public String deleteById(Long id) {
        Holiday holiday = holidayRepository.findById(id).get();
        holidayRepository.deleteById(holiday.getId());
        return "Holiday successfully deleted!";
    }

    public Holiday mapToEntity(HolidayRequest holidayRequest) {
        Optional<User> user = userRepository.findById(holidayRequest.getUser_id());
        Holiday holiday = new Holiday();
        BeanUtils.copyProperties(holidayRequest, user);
        holiday.setHolidayName(holidayRequest.getHolidayName());
        holiday.setDate(holidayRequest.getDate());
        holiday.setImage(holidayRequest.getImage());
        holiday.setUser_id(holidayRequest.getUser_id());
        holiday.setUser(user.get());
        if (holidayRequest.getImage() == null) {
            holiday.setImage("https://funart.pro/17017-velikij-chelovek-pauk-44-foto.html");
        } else {
            holiday.setImage(holidayRequest.getImage());
        }
        return holiday;
    }


    public HolidayResponse holidayResponse(Holiday holiday) {
        return HolidayResponse.builder()
                .id(holiday.getId())
                //  .user_id(holiday.getUser_id())
                // .user(holiday.getUser())
                .holidayName(holiday.getHolidayName())
                .date(holiday.getDate())
                .image(holiday.getImage())
                .build();
    }

    public List<HolidayResponse> getAllHolidays() {
        return view(holidayRepository.findAll());
    }

    public List<HolidayResponse> view(List<Holiday> holidays) {
        List<HolidayResponse> responses = new ArrayList<>();
        for (Holiday holiday : holidays) {
            responses.add(holidayResponse(holiday));
        }
        return responses;
    }


}

