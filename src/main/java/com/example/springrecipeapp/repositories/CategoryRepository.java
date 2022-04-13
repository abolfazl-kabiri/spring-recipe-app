package com.example.springrecipeapp.repositories;

import com.example.springrecipeapp.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    void deleteByCategoryName(String categoryName);

    Optional<Category> findByCategoryName(String categoryName);
}
