package com.peaksoft.controller;

import com.peaksoft.dto.CategoryRequest;
import com.peaksoft.dto.CategoryResponse;
import com.peaksoft.model.entity.Category;
import com.peaksoft.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse create(@RequestBody CategoryRequest request){
        return categoryService.create(request);
    }

    @PutMapping("{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest request){
        return categoryService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
    }

    @GetMapping("{id}")
    public CategoryResponse getById(@PathVariable Long id){
        return categoryService.getById(id);
    }


    @GetMapping("/all")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
}
