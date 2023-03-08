package com.peaksoft.controller;

import com.peaksoft.dto.CharityRequest;
import com.peaksoft.dto.CharityResponse;
import com.peaksoft.dto.CharityResponseView;
import com.peaksoft.model.entity.Charity;
import com.peaksoft.service.CharityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charity")
public class CharityController {

    private final CharityService charityService;

    @PostMapping
    public CharityResponse create(@RequestBody CharityRequest request){
        return charityService.create(request);
    }

    @PutMapping("{id}")
    public CharityResponse update(@PathVariable Long id, @RequestBody CharityRequest request){
        return charityService.update(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        charityService.deleteById(id);
    }

    @GetMapping("{id}")
    public CharityResponse getById(@PathVariable Long id){
        return charityService.getById(id);
    }


    @GetMapping("/all")
    public List<Charity> getAllCharity(){
        return charityService.getAllCharity();
    }

    @GetMapping
    public CharityResponseView getAll(@RequestParam(name = "text", required = false) String text,
                                      @RequestParam int page,
                                      @RequestParam int size){
        return charityService.getAllCharityPagination(text, page, size);
    }

}
