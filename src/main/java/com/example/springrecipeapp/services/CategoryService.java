package com.example.springrecipeapp.services;

import com.example.springrecipeapp.models.Category;

import java.util.Set;

public interface CategoryService {

    Category save(Category category);

    void delete(Category category);

    void deleteByCategoryName(String categoryName);

    Set<Category> findAll();

    Category findByCategoryName(String categoryName);
}
