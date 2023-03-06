package com.peaksoft.service;

import com.peaksoft.dto.CharityRequest;
import com.peaksoft.dto.CharityResponse;
import com.peaksoft.dto.CharityResponseView;
import com.peaksoft.model.User;
import com.peaksoft.model.entity.Category;
import com.peaksoft.model.entity.Charity;
import com.peaksoft.model.entity.Subcategory;
import com.peaksoft.repository.CategoryRepository;
import com.peaksoft.repository.CharityRepository;
import com.peaksoft.repository.SubCategoryRepository;
import com.peaksoft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharityService {

    private final CharityRepository charityRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	private final SubCategoryRepository subCategoryRepository;

    public CharityResponse create(CharityRequest request){
		Charity charity = mapToEntity(request);
		charityRepository.save(charity);
		return charityResponse(charity);
	}

	public CharityResponse update(Long id, CharityRequest request){
		Charity charity = charityRepository.findById(id).get();
		mapToUpdate(request,charity);
		return charityResponse(charityRepository.save(charity));
	}

	public CharityResponse deleteById(Long id){
		Charity charity = charityRepository.findById(id).get();

		charityRepository.deleteById(id);
		return charityResponse(charity);
	}

	public CharityResponse getById(Long id){
		Charity charity = charityRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("charity is not found"+id)));

		return charityResponse(charity);
	}

	public Charity mapToUpdate(CharityRequest request, Charity charity){
		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found with id " + request.getUserId()));
		charity.setUser(user);
		charity.setUserId(request.getUserId());
		Charity charity1 = new Charity();
		BeanUtils.copyProperties(request,charity1);
		charity.setUserId(request.getUserId());
		charity.setCharityName(request.getCharityName());
		charity.setDescription(request.getDescription());
		charity.setCharityDate(LocalDate.now());
		charity.setImage(request.getImage());
		charity.setCondition(request.getCondition());
		charity.setCountry(request.getCountry());

		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new NotFoundException("Category not found with id " + request.getCategoryId()));
		charity.setCategory(category);

		Subcategory subCategory = subCategoryRepository.findById(request.getSubCategoryId())
				.orElseThrow(() -> new NotFoundException("SubCategory not found with id " + request.getSubCategoryId()));
		charity.setSubCategory(subCategory);
		charity.setSubCategoryId(request.getSubCategoryId());

		charity.setCharityStatus(request.getCharityStatus());

		return charity;
	}

	public Charity mapToEntity(CharityRequest request) {
		Charity charity = new Charity();
		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found with id " + request.getUserId()));
		charity.setUser(user);
		charity.setUserId(request.getUserId());

		charity.setCharityName(request.getCharityName());
		charity.setImage(request.getImage());
		charity.setDescription(request.getDescription());
		charity.setCondition(request.getCondition());
		charity.setCountry(request.getCountry());
		charity.setCharityStatus(request.getCharityStatus());


		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new NotFoundException("Category not found with id " + request.getCategoryId()));
		charity.setCategory(category);
		charity.setCategoryId(request.getCategoryId());

		Subcategory subCategory = subCategoryRepository.findById(request.getSubCategoryId())
				.orElseThrow(() -> new NotFoundException("SubCategory not found with id " + request.getSubCategoryId()));
		charity.setSubCategory(subCategory);
		charity.setSubCategoryId(request.getSubCategoryId());

        return charity;
    }


	public CharityResponse charityResponse(Charity charity) {
		return CharityResponse.builder()
				.id(charity.getId())
				.charityName(charity.getCharityName())
				.charityDate(LocalDate.now())
				.image(charity.getImage())
				.description(charity.getDescription())
				.condition(charity.getCondition())
				.country(charity.getCountry())
				.userId(charity.getUser().getId()) // устанавливаем id пользователя
				.categoryId(charity.getCategory().getId())
				.subCategoryId(charity.getSubCategory().getId())
				.charityStatus(charity.getCharityStatus())
				.build();
	}


	public List<Charity> getAllCharity() {
		return charityRepository.findAll();
	}

	public CharityResponseView getAllCharityPagination(String text, int page, int size){
        CharityResponseView responseView = new CharityResponseView();
        PageRequest pageable =  PageRequest.of(page -1, size);
        responseView.setResponses(view(search(text, pageable)));
        return responseView;
    }

    public List<CharityResponse> view(List<Charity> charities){
        List<CharityResponse> responses = new ArrayList<>();
        for (Charity charity:charities) {
            responses.add(charityResponse(charity));
        }
        return  responses;
    }

    private List<Charity> search(String name, PageRequest pageable) {
        if (name == null) {
            return charityRepository.getByPagination(pageable);
        } else {

            return charityRepository.searchAndPagination(name.toUpperCase(), pageable);
        }
    }
}
