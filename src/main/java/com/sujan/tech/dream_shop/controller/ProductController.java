package com.sujan.tech.dream_shop.controller;

import com.sujan.tech.dream_shop.dto.ProductDto;
import com.sujan.tech.dream_shop.model.Category;
import com.sujan.tech.dream_shop.model.Product;
import com.sujan.tech.dream_shop.request.AddProductRequest;
import com.sujan.tech.dream_shop.request.ProductUpdateRequest;
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
    List<ProductDto> productDtos = productService.getConvertedProducts(products);
    return ResponseEntity.ok(new ApiResponse("success",productDtos));
  }

  @GetMapping("product/{id}")
  public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id){
    try{
      Product product = productService.getProductById(id);
      ProductDto productDto = productService.convertToDto(product);
      return ResponseEntity.ok(new ApiResponse("sucess",productDto));
    } catch(Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
              new ApiResponse("Invalid input",e.getMessage())
      );
    }
  }

  @GetMapping("/{name}/product")
  public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name){
    try{
      List<Product> products = productService.getProductsByName(name);
       List<ProductDto> productDtos = productService.getConvertedProducts(products);
      return ResponseEntity.ok(new ApiResponse("Found",productDtos));
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

  @GetMapping("/products/by-category-and-brand")
  public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@PathVariable String category,@PathVariable String brand){
    try{
      List<Product> products = productService.getProductsByCategoryAndBrand(category,brand);
        List<ProductDto> productDtos = productService.getConvertedProducts(products);
      return ResponseEntity.ok(new ApiResponse("Found",productDtos));
    }
    catch(Exception e){
     return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
  }

  @PutMapping("/product/{productId}/update")
  public  ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId) {
    try {
      Product product = productService.updateProduct(request, productId);
      return ResponseEntity.ok(new ApiResponse("Update product success!", product));
    } catch (Exception e) {
      return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
  }

  @DeleteMapping("/product/{productId}/delete")
  public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
    try {
      productService.deleteProductById(productId);
      return ResponseEntity.ok(new ApiResponse("Delete product success!", productId));
    } catch (Exception e) {
      return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
  }

  @GetMapping("/product/{brand}/fetch")
  public ResponseEntity<ApiResponse> findProductByBrand(@PathVariable String brand) {
    try{
      List<Product> products = productService.getProductsByBrand(brand);
      List<ProductDto> productDtos = productService.getConvertedProducts(products);
      return ResponseEntity.ok(new ApiResponse("Found",productDtos));
    } catch(Exception e){
      return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
  }

  @GetMapping("/products/by/brand-and-name")
  public ResponseEntity<ApiResponse> findProductByBrandAndName(@PathVariable String brand,@PathVariable String name){
    try{
      List<Product> products = productService.getProductsByBrandAndName(brand,name);
      return ResponseEntity.ok(new ApiResponse("Found",products));
    } catch(Exception e) {
      return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
    }
  }
  @GetMapping("/product/{category}/all/products")
  public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category) {
      try {
          List<Product> products = productService.getProductsByCategory(category);
          if (products != null && products.isEmpty()) {
              return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
          }
          //List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
          return  ResponseEntity.ok(new ApiResponse("success", products));
      } catch (Exception e) {
          return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
      }
  }

  @GetMapping("/product/count/by-brand/and-name")
  public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
      try {
          var productCount = productService.countProductsByBrandAndName(brand, name);
          return ResponseEntity.ok(new ApiResponse("Product count!", productCount));
      } catch (Exception e) {
          return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
      }
  }

 @GetMapping("/products/by/brand-and-name")
  public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
      try {
          List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
          if (products.isEmpty()) {
              return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found ", null));
          }
          //List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
          return  ResponseEntity.ok(new ApiResponse("success", products));
      } catch (Exception e) {
          return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
      }
  }
}
