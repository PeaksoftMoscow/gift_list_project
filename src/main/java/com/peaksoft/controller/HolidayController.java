package com.peaksoft.controller;

import com.peaksoft.halidayCode.Servise.HolidayServise;
import com.peaksoft.halidayCode.dto.HolidayRequest;
import com.peaksoft.halidayCode.dto.HolidayResponse;
import com.peaksoft.model.entity.Holiday;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/holiday")
public class HolidayController {
private final HolidayServise holidayServise;

@PostMapping
public Holiday create(@RequestBody HolidayRequest request) {
	return holidayServise.create(request, request.getUser_id());
}

@PutMapping("{id}")
public HolidayResponse update(@PathVariable Long id, @RequestBody HolidayRequest request) {
	return holidayServise.update(id, request);
}

@DeleteMapping("{id}")
public String deleteById(@PathVariable Long id) {
	return holidayServise.deleteById(id);
}

@GetMapping("{id}")
public HolidayResponse getById(@PathVariable Long id) {
	return holidayServise.findById(id);
}

@GetMapping("/all")
public List<HolidayResponse> getAllCourse() {
	return holidayServise.getAllHolidays();
}


}


