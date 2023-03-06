package com.peaksoft.api;

import com.peaksoft.dto.WishListRequest;
import com.peaksoft.dto.WishListResponse;
import com.peaksoft.entity.WishList;
import com.peaksoft.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishList")
public class WishListController {

    private final WishListService wishListService;


    @GetMapping("/{id}")
    public WishList getById(@PathVariable Long id) {
        return wishListService.findByIdWish(id);
    }

    @GetMapping("/all")
    public List<WishList> getAll() {
        return wishListService.getAll();
    }

    @PostMapping
    public WishListResponse save(@RequestBody WishListRequest request) {
        return wishListService.create(request);
    }

    @PatchMapping("/{id}")
    public WishListResponse update(@RequestBody WishListRequest request, @PathVariable Long id) {
        return wishListService.update(request, id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        wishListService.deleteById(id);

    }

}
