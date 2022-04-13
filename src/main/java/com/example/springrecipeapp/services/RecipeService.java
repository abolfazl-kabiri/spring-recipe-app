package com.example.springrecipeapp.services;

import com.example.springrecipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

//    void delete(Recipe recipe);
//    void deleteByDescription(String description);
//    Recipe save(Recipe recipe);
    Set<Recipe> getRecipes();
//    Recipe findByDescription(String description);


}
