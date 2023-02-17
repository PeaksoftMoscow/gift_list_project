package com.peaksoft.mapper;

import com.peaksoft.dto.ProfileRequest;
import com.peaksoft.dto.ProfileResponse;
import com.peaksoft.model.entity.User;
import com.peaksoft.model.entity.ShoeSize;
import com.peaksoft.model.entity.enums.Country;
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

              public void mapToEntity(User user,ProfileRequest profileRequest) {
          user.setFirstName(profileRequest.getFirstName());
          user.setLastName(profileRequest.getLastName());
          user.setEmail(profileRequest.getEmail());
          user.setNumber(profileRequest.getNumber());
          user.setCountry(Country.valueOf(String.valueOf(profileRequest.getCountry())));
          user.setDateOfBirth(profileRequest.getDateOfBirth());
         List<ShoeSize> shoeSize = shoeSizeRepository.findAll();
//          if (profileRequest.getShoeSizes() == null) {
//            shoeSize.forEach(a -> a.setUser(user));
//              throw new NotFoundException("Shoe size  not given");
 //       }
          user.setShoeSizes(profileRequest.getShoeSizes());
         shoeSize.forEach(a -> a.setUser(user));
         user.setShoeSizes(profileRequest.getShoeSizes());

        /*ClothingSize clothingSize = clothingSizeRepository.findById(profileRequest.getClothingSizesId()).get();
             *         user.setClothingSizes((List<ClothingSize>) clothingSize);
             *         ShoeSize shoeSize = shoeSizeRepository.findById(profileRequest.getShoeSizesId()).get();
             *         user.setShoeSizes((List<ShoeSize>) shoeSize);*/
    user.setImportantToKnow(profileRequest.getImportantToKnow());
         user.setHobbies(profileRequest.getHobbies());

         }

                 private User getObject(Long id){
        return userRepository.findById(id).orElseThrow(()->
                    new NotFoundException(
                    String.format("Company with %d id not found!",id)));
        }


                public ProfileResponse mapToResponse(User user){
        ProfileResponse profileResponse=new ProfileResponse();
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setNumber(user.getNumber());
        profileResponse.setCountry(user.getCountry());
        profileResponse.setDateOfBirth(user.getDateOfBirth());
                // profileResponse.setClothingSizes(user.getClothingSizes());
        profileResponse.setShoeSizes(user.getShoeSizes());
        profileResponse.setImportantToKnow(user.getImportantToKnow());
        profileResponse.setHobbies(user.getHobbies());

        return profileResponse;
        }
}
