package com.peaksoft.controller;

import com.peaksoft.dto.SubCategoryRequest;
import com.peaksoft.dto.SubCategoryResponse;
import com.peaksoft.model.entity.Subcategory;
import com.peaksoft.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subcategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
    public SubCategoryResponse create(@RequestBody SubCategoryRequest request){
        return subCategoryService.create(request);
    }

    @PutMapping("{id}")
    public SubCategoryResponse update(@PathVariable Long id, @RequestBody SubCategoryRequest request){
        return subCategoryService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        subCategoryService.deleteById(id);
    }

    @GetMapping("{id}")
    public SubCategoryResponse getById(@PathVariable Long id){
        return subCategoryService.getById(id);
    }


    @GetMapping("/all")
    public List<Subcategory> getAllSubCategory(){
        return subCategoryService.getAllSubCategory();
    }
}
