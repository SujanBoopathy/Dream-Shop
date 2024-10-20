package com.sujan.tech.dream_shop.service.category;

import com.sujan.tech.dream_shop.model.Category;
import com.sujan.tech.dream_shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long id) throws Exception {
        return categoryRepository.findById(id).orElseThrow(() -> new Exception("Unknown Category!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }
}
