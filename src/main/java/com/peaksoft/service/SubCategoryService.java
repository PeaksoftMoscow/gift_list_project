package com.peaksoft.service;


import com.peaksoft.dto.SubCategoryRequest;
import com.peaksoft.dto.SubCategoryResponse;
import com.peaksoft.model.entity.Category;
import com.peaksoft.model.entity.Subcategory;
import com.peaksoft.repository.CategoryRepository;
import com.peaksoft.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    private final CategoryRepository categoryRepository;

    public SubCategoryResponse create(SubCategoryRequest request){
        Subcategory subCategory = mapToEntity(request);
        subCategoryRepository.save(subCategory);
        return subCategoryResponse(subCategory);
    }

    public SubCategoryResponse update(Long id, SubCategoryRequest request){
        Subcategory subCategory = subCategoryRepository.findById(id).get();
        mapToUpdate(request,subCategory);
        return subCategoryResponse(subCategoryRepository.save(subCategory));
    }

    public SubCategoryResponse deleteById(Long id){
        Subcategory subCategory = subCategoryRepository.findById(id).get();

        subCategoryRepository.deleteById(id);
        return subCategoryResponse(subCategory);
    }

    public SubCategoryResponse getById(Long id){
        Subcategory category = subCategoryRepository.findById(id).get();
        subCategoryRepository.findById(id);
        return subCategoryResponse(category);
    }

    public Subcategory mapToUpdate(SubCategoryRequest request, Subcategory subCategory){
        subCategory.setSubcategoryName(request.getSubcategoryName());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id " + request.getCategoryId()));
        subCategory.setCategory(category);
        subCategory.setCategoryId(request.getCategoryId());
        return subCategory;
    }

    public Subcategory mapToEntity(SubCategoryRequest request) {
        Subcategory subCategory = new Subcategory();
        subCategory.setSubcategoryName(request.getSubcategoryName());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id " + request.getCategoryId()));
        subCategory.setCategory(category);
        subCategory.setCategoryId(request.getCategoryId());

        return subCategory;
    }


    public SubCategoryResponse subCategoryResponse(Subcategory subCategory) {
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .subcategoryName(subCategory.getSubcategoryName())
                .categoryId(subCategory.getCategory().getId())
                .build();
    }

    public List<Subcategory> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }
}
