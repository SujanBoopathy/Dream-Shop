package com.sujan.tech.dream_shop.controller;

import com.sujan.tech.dream_shop.model.Category;
import com.sujan.tech.dream_shop.model.Product;
import com.sujan.tech.dream_shop.request.AddProductRequest;
import com.sujan.tech.dream_shop.respone.ApiResponse;
import com.sujan.tech.dream_shop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

  @GetMapping("/{name}/product")
  public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
    try{
      List<Product> products = productService.getProductsByName(name);
      return ResponseEntity.ok(new ApiResponse("Found",products));
    } catch(Exception e){
      return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
    }
  }

  @PostMapping("/add")
  public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
    try {
      Product theProduct = productService.addProduct(product);
      return ResponseEntity.ok(new ApiResponse("Add product success!", product));
    } catch (Exception e) {
      return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
    }
  }
  
}
