package com.sujan.tech.dream_shop.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return  ResponseEntity.ok(new ApiResponse("Found!", categories));
        } catch (Exception e) {
           return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:", INTERNAL_SERVER_ERROR));
        }
    }
}
