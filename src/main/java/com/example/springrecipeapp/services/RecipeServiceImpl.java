package com.example.springrecipeapp.services;

import com.example.springrecipeapp.commands.RecipeCommand;
import com.example.springrecipeapp.converters.RecipeCommandToRecipe;
import com.example.springrecipeapp.converters.RecipeToRecipeCommand;
import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommandConverter;
    private final RecipeCommandToRecipe recipeCommandToRecipeConverter;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeToRecipeCommand recipeToRecipeCommandConverter, RecipeCommandToRecipe recipeCommandToRecipeConverter) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommandConverter = recipeToRecipeCommandConverter;
        this.recipeCommandToRecipeConverter = recipeCommandToRecipeConverter;
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
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
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Recipe> saveAll(List<Recipe> recipes) {
        return recipeRepository.saveAll(recipes);
    }

    @Override
    public RecipeCommand saveCommand(RecipeCommand command) {
        Recipe savedRecipe = save(recipeCommandToRecipeConverter.convert(command));
        return recipeToRecipeCommandConverter.convert(savedRecipe);
    }
}
