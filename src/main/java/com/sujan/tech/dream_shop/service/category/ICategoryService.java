package com.sujan.tech.dream_shop.service.category;

import com.sujan.tech.dream_shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id) throws Exception;
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category) throws Exception;
    Category updateCategory(Category category,Long id) throws Exception;
    void deleteCategoryById(Long id) throws Exception;

}
