package com.springboot.photocollectionapi.controller;

import com.springboot.photocollectionapi.dto.CategoryDto;
import com.springboot.photocollectionapi.exception.ApiResponse;
import com.springboot.photocollectionapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    //create
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto createCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

    }

    // update
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully..", true), HttpStatus.OK);
    }

    // get
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
        CategoryDto getCategoryById = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDto>(getCategoryById, HttpStatus.OK);
    }

    //get all
    @GetMapping("/AllCategory")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> getAllCategory = categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(getAllCategory, HttpStatus.OK);
    }
}
