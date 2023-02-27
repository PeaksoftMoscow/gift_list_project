package com.peaksoft;

import com.peaksoft.dto.UserRequest;
import com.peaksoft.halidayCode.Servise.HolidayServise;
import com.peaksoft.halidayCode.dto.HolidayRequest;
import com.peaksoft.halidayCode.dto.HolidayResponse;
import com.peaksoft.halidayCode.repository.HolidayRepository;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.Holiday;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.service.UserService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private HolidayRepository holidayRepository;

    @InjectMocks
    private HolidayServise holidayService;

    @Mock
    private UserService userService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        holidayService = new HolidayServise(holidayRepository, userRepository);
    }


    @Test
    @Order(1)
    public void createHoliday() {
        UserRequest userRequest = new UserRequest();
        userService.register(userRequest);
        User user = new User();
        user.setBlocked(false);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        HolidayRequest holidayRequest = new HolidayRequest();
        holidayRequest.setHolidayName("Day developer");
        holidayRequest.setImage("Image");
        Holiday holiday = new Holiday();
        holiday.setImage(holidayRequest.getImage());
        holiday.setHolidayName(holidayRequest.getHolidayName());
        holidayService.create(holidayRequest, user.getId());

        verify(holidayRepository).save(any(Holiday.class));
        assertEquals(holidayRequest.getImage(), holiday.getImage());
        assertEquals(holidayRequest.getHolidayName(), holiday.getHolidayName());
        Assert.notNull(holidayRequest);
    }

    @Test
    @Order(2)
    void deleteTest() {
        Long id = 1L;
        Holiday holiday = new Holiday();
        holiday.setId(id);

        when(holidayRepository.findById(id)).thenReturn(Optional.of(holiday));

        String result = holidayService.deleteById(id);

        verify(holidayRepository, times(1)).deleteById(id);
        assertEquals("Holiday successfully deleted!", result);
    }


    @Test
    void testFindById() {

        Long id = 1L;
        Holiday holiday = new Holiday();
        holiday.setId(id);
        when(holidayRepository.findById(id)).thenReturn(Optional.of(holiday));

        HolidayResponse holidayResponse = holidayService.findById(id);

        assertEquals(holidayResponse.getId(), id);
    }


    @Test
    public void testGetAll() {

        Holiday holiday1 = new Holiday();
        holiday1.setId(1L);

        Holiday holiday2 = new Holiday();
        holiday2.setId(2L);

        List<Holiday> holidays = new ArrayList<>();
        holidays.add(holiday1);
        holidays.add(holiday2);

        when(holidayRepository.findAll()).thenReturn(holidays);

        List<HolidayResponse> expectedResponses = new ArrayList<>();
        expectedResponses.add(holidayResponse(holiday1));
        expectedResponses.add(holidayResponse(holiday2));

        List<HolidayResponse> actualResponses = holidayService.getAllHolidays();

        assertEquals(expectedResponses.size(), actualResponses.size());

        for (int i = 0; i < expectedResponses.size(); i++) {
            HolidayResponse expectedResponse = expectedResponses.get(i);
            HolidayResponse actualResponse = actualResponses.get(i);
            assertEquals(expectedResponse.getId(), actualResponse.getId());
        }
    }

    private HolidayResponse holidayResponse(Holiday holiday) {
        HolidayResponse response = holidayService.holidayResponse(holiday);
        response.setId(holiday.getId());
        return response;
    }


        @Test
        public void testUpdateHoliday() {

            Long id = 1L;
            HolidayRequest holidayRequest = new HolidayRequest();
            holidayRequest.setHolidayName("Before update");
            holidayRequest.setDate(LocalDate.of(2023, 12, 25));
            holidayRequest.setImage("https://example.com/holiday.jpg");

            Holiday holiday = new Holiday();
            holiday.setId(id);
            holiday.setHolidayName("After update");
            holiday.setDate(LocalDate.of(2023, 12, 25));
            holiday.setImage("https://example.com/holiday.jpg1");


            when(holidayRepository.findById(id)).thenReturn(Optional.of(holiday));
            when(holidayRepository.save(holiday)).thenReturn(holiday);

            HolidayResponse expectedResponse = holidayResponse(holiday);
            expectedResponse.setHolidayName(holidayRequest.getHolidayName());
            expectedResponse.setDate(holidayRequest.getDate());
            expectedResponse.setImage(holidayRequest.getImage());


            HolidayResponse actualResponse = holidayService.update(id, holidayRequest);


            assertEquals(expectedResponse.getId(), actualResponse.getId());
            assertEquals(expectedResponse.getHolidayName(), actualResponse.getHolidayName());
            assertEquals(expectedResponse.getDate(), actualResponse.getDate());
            assertEquals(expectedResponse.getImage(), actualResponse.getImage());
        }

}


