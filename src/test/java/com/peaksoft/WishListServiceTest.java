package com.peaksoft;

import com.peaksoft.dto.UserRequest;
import com.peaksoft.dto.WishListRequest;
import com.peaksoft.dto.WishListResponse;
import com.peaksoft.halidayCode.Servise.HolidayServise;
import com.peaksoft.halidayCode.dto.HolidayRequest;
import com.peaksoft.halidayCode.repository.HolidayRepository;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.Holiday;
import com.peaksoft.model.entity.WishList;
import com.peaksoft.repository.UserRepository;
import com.peaksoft.repository.WishListRepository;
import com.peaksoft.service.UserService;
import com.peaksoft.service.WishListService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishListServiceTest {


    @Mock
    private WishListRepository wishListRepository;
    @Mock
    private AutoCloseable autoCloseable;
    @Mock
    private HolidayRepository holidayRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private WishListService wishListService = new WishListService(wishListRepository, userRepository, holidayRepository);
    @Mock
    private UserService userService;
    @Mock
    private HolidayServise holidayServise;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        wishListService = new WishListService(wishListRepository, userRepository, holidayRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Order(1)
    void testCreate() {
        UserRequest userRequest = new UserRequest();
        userService.register(userRequest);
        User user = new User();
        user.setBlocked(false);

        HolidayRequest holidayRequest = new HolidayRequest();
        holidayRequest.setHolidayName("Day developer");
        holidayRequest.setImage("Image");

        Holiday holiday = new Holiday();
        holiday.setHolidayName(holidayRequest.getHolidayName());
        holiday.setImage(holidayRequest.getImage());
        holidayServise.create(holidayRequest, user.getId());

        when(holidayRepository.findById(holiday.getId())).thenReturn(Optional.of(holiday));


        WishListRequest wishListRequest = new WishListRequest();
        wishListRequest.setGiftName("Phone");
        wishListRequest.setImage("Image");

        WishList wishList = new WishList();
        wishList.setHolidayId(wishListRequest.getHolidayId());
        wishList.setGiftName(wishListRequest.getGiftName());
        wishList.setImage(wishListRequest.getImage());
        wishList.setUser(user);
        wishListService.create(wishListRequest);

        verify(wishListRepository).save(any(WishList.class));
        assertEquals(wishListRequest.getImage(), wishList.getImage());
        assertEquals(wishListRequest.getGiftName(), wishList.getGiftName());
        Assert.notNull(wishListRequest);


    }

    @Test
    @Order(2)
    void deleteWishList() {
        WishList wishList = new WishList();

        wishListRepository.save(wishList);
        Long wishListId = wishList.getId();
        wishListRepository.deleteById(wishListId);
        WishList wishList1 = wishListRepository.getById(wishListId);
        assertNull(wishList1);

    }

    @Test
    @Order(3)
    void findById() {
        Long id = 1L;
        WishList wishList = new WishList();
        wishList.setId(id);

        when(wishListRepository.findById(id)).thenReturn(Optional.of(wishList));

        WishList result = wishListService.findByIdWish(id);

        Assertions.assertEquals(wishList, result);
    }


    @Test
    @Order(4)
    public void testGetAll() {

        List<WishList> expectedWishLists = new ArrayList<>();
        expectedWishLists.add(new WishList());
        expectedWishLists.add(new WishList());
        when(wishListRepository.findAll()).thenReturn(expectedWishLists);

        List<WishList> actualWishLists = wishListService.getAll();

        assertEquals(expectedWishLists.size(), actualWishLists.size());
        for (int i = 0; i < expectedWishLists.size(); i++) {
            assertEquals(expectedWishLists.get(i), actualWishLists.get(i));
        }
    }

    @Test
    @Order(5)
    public void testUpdateWishList() {

        Long id = 1L;
        WishListRequest request = new WishListRequest();
        request.setGiftName("Updated giftName");
        request.setDescription("Updated description");
        request.setImage("https://example.com/updated-image");
        request.setHolidayId(1L);
        request.setHolidayDate(LocalDate.of(2023, 12, 25));

        WishList wishList = new WishList();
        wishList.setId(id);
        wishList.setGiftName("Original Gift");
        wishList.setLink("https://example.com/original-gift");
        wishList.setDescription("Original description");
        wishList.setImage("https://example.com/original-image");
        wishList.setHolidayId(1L);
        wishList.setHolidayDate(LocalDate.of(2023, 10, 31));

        when(wishListRepository.findById(id)).thenReturn(Optional.of(wishList));
        when(wishListRepository.save(wishList)).thenReturn(wishList);

        WishListResponse expectedResponse = wishListResponse(wishList);
        expectedResponse.setGiftName(request.getGiftName());
        expectedResponse.setLink(request.getLink());
        expectedResponse.setDescription(request.getDescription());
        expectedResponse.setImage(request.getImage());
        expectedResponse.setHolidayId(request.getHolidayId());
        expectedResponse.setHolidayDate(request.getHolidayDate());

        WishListResponse actualResponse = wishListService.update(request, id);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getGiftName(), actualResponse.getGiftName());
        assertEquals(expectedResponse.getLink(), actualResponse.getLink());
        assertEquals(expectedResponse.getDescription(), actualResponse.getDescription());
        assertEquals(expectedResponse.getImage(), actualResponse.getImage());
        assertEquals(expectedResponse.getHolidayId(), actualResponse.getHolidayId());
        assertEquals(expectedResponse.getHolidayDate(), actualResponse.getHolidayDate());
    }

    private WishListResponse wishListResponse(WishList wishList) {
        WishListResponse response = wishListService.wishListResponse(wishList);
        response.setId(wishList.getId());
        return response;
    }
}


