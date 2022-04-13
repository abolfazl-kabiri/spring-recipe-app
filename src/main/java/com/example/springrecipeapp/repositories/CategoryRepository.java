package com.example.springrecipeapp.repositories;

import com.example.springrecipeapp.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
