package com.example.springrecipeapp.services;

import com.example.springrecipeapp.commands.RecipeCommand;
import com.example.springrecipeapp.models.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    void delete(Recipe recipe);
    void deleteById(Long id);
    Recipe save(Recipe recipe);
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    Iterable<Recipe> saveAll(List<Recipe> recipes);
    RecipeCommand saveCommand(RecipeCommand command);

}
