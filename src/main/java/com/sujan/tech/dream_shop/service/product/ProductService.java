package com.sujan.tech.dream_shop.service.product;

import com.sujan.tech.dream_shop.exception.ProductNotFoundException;
import com.sujan.tech.dream_shop.model.Category;
import com.sujan.tech.dream_shop.model.Product;
import com.sujan.tech.dream_shop.repository.CategoryRepository;
import com.sujan.tech.dream_shop.repository.ProductRepository;
import com.sujan.tech.dream_shop.request.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product addProduct(AddProductRequest request) {
        //check if category is found in the DB
        // If yes, set it as the new product category
        // If no, then save it as a new category
        // The set as the new product category.
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                   Category newCategory = new Category(request.getCategory().getName());
                   return categoryRepository.save(newCategory);
                });
        return new Product();
    }

    private Product createProduct(AddProductRequest request,Category category){
        return new Product(
            request.getName(), request.getBrand(),request.getPrice(),request.getQuantity(),request.getDescription(),category
        );
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long id){
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        ()-> {
                            try {
                                throw new ProductNotFoundException("Product not found");
                            } catch (ProductNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        });
    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String productName) {
        return productRepository.findByBrandAndName(brand,productName);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
}
