package com.example.springrecipeapp.services;

import com.example.springrecipeapp.commands.RecipeCommand;
import com.example.springrecipeapp.models.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);

}
