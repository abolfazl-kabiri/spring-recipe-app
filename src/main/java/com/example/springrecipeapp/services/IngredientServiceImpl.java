package com.example.springrecipeapp.services;

import com.example.springrecipeapp.commands.IngredientCommand;
import com.example.springrecipeapp.converters.IngredientToIngredientCommand;
import com.example.springrecipeapp.models.Recipe;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final RecipeService recipeService;
    private final IngredientToIngredientCommand converter;

    public IngredientServiceImpl(RecipeService recipeService, IngredientToIngredientCommand converter) {
        this.recipeService = recipeService;
        this.converter = converter;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeService.findById(recipeId);

        if (recipe != null) {
            return recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .map(converter::convert).findFirst().orElse(null);
        }
        return null;
    }
}
