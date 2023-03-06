package com.peaksoft.mapper;

import com.peaksoft.dto.ProfileRequest;
import com.peaksoft.dto.ProfileResponse;
import com.peaksoft.model.entity.ClothingSize;
import com.peaksoft.model.entity.ShoeSize;
import com.peaksoft.model.entity.User;
import com.peaksoft.repository.ClothingSizeRepository;
import com.peaksoft.repository.ShoeSizeRepository;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProfileMapper {
    private final UserRepository userRepository;
    private final ShoeSizeRepository shoeSizeRepository;
    private final ClothingSizeRepository clothingSizeRepository;

    public void mapToEntity(User user, ProfileRequest profileRequest) {
        user.setFirstName(profileRequest.getFirstName());
        user.setLastName(profileRequest.getLastName());
        user.setEmail(profileRequest.getEmail());
        user.setNumber(profileRequest.getNumber());
        user.setCountry(profileRequest.getCountry());
        user.setDateOfBirth(profileRequest.getDateOfBirth());

        List<ShoeSize> shoeSize = shoeSizeRepository.findAll();
        if (profileRequest.getShoeSizes() == null) {
            shoeSize.forEach(a -> a.setUser(user));
            throw new NotFoundException("Shoe size  not given");
        }


        List<ClothingSize> clothingSize = clothingSizeRepository.findAll();
        user.setClothingSizes(profileRequest.getClothingSizes());
        clothingSize.forEach(a -> a.setUser(user));
        user.setClothingSizes(profileRequest.getClothingSizes());

        user.setImportantToKnow(profileRequest.getImportantToKnow());
        user.setHobbies(profileRequest.getHobbies());

    }


    public ProfileResponse mapToResponse(User user) {
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setNumber(user.getNumber());
        profileResponse.setCountry(user.getCountry());
        profileResponse.setDateOfBirth(user.getDateOfBirth());
        profileResponse.setClothingSizes(user.getClothingSizes());
        profileResponse.setShoeSizes(user.getShoeSizes());
        profileResponse.setImportantToKnow(user.getImportantToKnow());
        profileResponse.setHobbies(user.getHobbies());

        return profileResponse;
    }
}
