package com.example.springrecipeapp.services;

import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Override
    public void deleteByDescription(String description) {
        recipeRepository.deleteByDescription(description);
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findByDescription(String description) {
        return recipeRepository.findByDescription(description).orElse(null);
    }

    @Override
    public Iterable<Recipe> saveAll(List<Recipe> recipes) {
        return recipeRepository.saveAll(recipes);
    }


}
