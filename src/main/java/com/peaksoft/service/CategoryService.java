package com.peaksoft.service;

import com.peaksoft.dto.CategoryRequest;
import com.peaksoft.dto.CategoryResponse;
import com.peaksoft.dto.CharityRequest;
import com.peaksoft.model.entity.Category;
import com.peaksoft.model.entity.Charity;
import com.peaksoft.model.entity.Subcategory;
import com.peaksoft.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse create(CategoryRequest request) {
        Category category = mapToEntity(request);
        categoryRepository.save(category);
        return categoryResponse(category);
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id).get();
        mapToUpdate(request, category);
        return categoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse deleteById(Long id) {
        Category category = categoryRepository.findById(id).get();

        categoryRepository.deleteById(id);
        return categoryResponse(category);
    }

    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.findById(id);
        return categoryResponse(category);
    }

    public Category mapToUpdate(CategoryRequest request, Category category) {
        category.setCategoryName(request.getCategoryName());

        return category;
    }

    public Category mapToEntity(CategoryRequest request) {
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setCharities(request.getCharities());
        category.setSubcategories(request.getSubcategories());
        return category;
    }
//    public Category mapToEntity(CategoryRequest request) {
//        Category category = new Category();
//        category.setCategoryName(request.getCategoryName());
//
//        // Добавляем подкатегорию
//        Subcategory subcategory = new Subcategory();
//        subcategory.setSubcategoryName(subcategoryName);
//        category.setSubcategory(subcategory);
//
//        // Добавляем благотворительные организации
//        List<Charity> charitiesList = new ArrayList<>();
//        for (CharityRequest charityRequest : charities) {
//            Charity charity = new Charity();
//            charity.setName(charityRequest.getName());
//            charity.setDescription(charityRequest.getDescription());
//            charitiesList.add(charity);
//        }
//        category.setCharities(charitiesList);
//
//        return category;
//    }


    public CategoryResponse categoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
