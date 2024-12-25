package com.sujan.tech.dream_shop.service.product;

import com.sujan.tech.dream_shop.dto.ImageDto;
import com.sujan.tech.dream_shop.dto.ProductDto;
import com.sujan.tech.dream_shop.exception.ProductNotFoundException;
import com.sujan.tech.dream_shop.model.Category;
import com.sujan.tech.dream_shop.model.Image;
import com.sujan.tech.dream_shop.model.Product;
import com.sujan.tech.dream_shop.repository.CategoryRepository;
import com.sujan.tech.dream_shop.repository.ImageRepository;
import com.sujan.tech.dream_shop.repository.ProductRepository;
import com.sujan.tech.dream_shop.request.AddProductRequest;
import com.sujan.tech.dream_shop.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

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
        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
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
    public Product updateProduct(ProductUpdateRequest request, Long productId) throws Exception{
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct,request))
                .map(productRepository :: save)
                .orElseThrow(() -> new ProductNotFoundException(("Product Not Found")));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return  existingProduct;
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

    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product,ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
