package com.example.springrecipeapp.services;

import com.example.springrecipeapp.commands.IngredientCommand;
import com.example.springrecipeapp.converters.IngredientCommandToIngredient;
import com.example.springrecipeapp.converters.IngredientToIngredientCommand;
import com.example.springrecipeapp.models.Ingredient;
import com.example.springrecipeapp.models.Recipe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final RecipeService recipeService;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientServiceImpl(RecipeService recipeService,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Recipe recipe = recipeService.findById(recipeId);

        if (recipe != null) {
            return recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .map(ingredientToIngredientCommand::convert).findFirst().orElse(null);
        }
        return null;
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Recipe recipe = recipeService.findById(command.getRecipeId());

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setDescription(command.getDescription());
            ingredient.setAmount(command.getAmount());
            ingredient.setUnitOfMeasure(unitOfMeasureService.findById(command.getUnitOfMeasure().getId()));
        } else {
            recipe.addIngredient(ingredientCommandToIngredient.convert(command));
        }

        Recipe savedRecipe = recipeService.save(recipe);
        return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst().get());
    }
}
