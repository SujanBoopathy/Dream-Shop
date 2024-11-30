package com.sujan.tech.dream_shop.controller;

import com.sujan.tech.dream_shop.model.Product;
import com.sujan.tech.dream_shop.respone.ApiResponse;
import com.sujan.tech.dream_shop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
  private final IProductService productService;

  @GetMapping("/all")
  public ResponseEntity<ApiResponse> getAllProducts(){
    List<Product> products = productService.getAllProducts();
    return ResponseEntity.ok(new ApiResponse("success",products));
  }

  @GetMapping("product/{id}")
  public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
    try{
      Product product = productService.getProductById(id);
      return ResponseEntity.ok(new ApiResponse("sucess",product));
    } catch(Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
              new ApiResponse("Invalid input",e.getMessage())
      );
    }
  }
  
}
