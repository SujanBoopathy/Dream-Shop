package com.sujan.tech.dream_shop.service.product;

import com.sujan.tech.dream_shop.dto.ProductDto;
import com.sujan.tech.dream_shop.exception.ProductNotFoundException;
import com.sujan.tech.dream_shop.model.Product;
import com.sujan.tech.dream_shop.request.AddProductRequest;
import com.sujan.tech.dream_shop.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest productRequest);
    Product getProductById(Long id) throws ProductNotFoundException;
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest productUpdateRequest , Long productId) throws Exception;
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String productName);
    List<Product> getProductsByBrandAndName(String brand,String productName);
    Long countProductsByBrandAndName(String brand,String name);
    ProductDto convertToDto(Product product);
    List<ProductDto> getConvertedProducts(List<Product> products);


}
