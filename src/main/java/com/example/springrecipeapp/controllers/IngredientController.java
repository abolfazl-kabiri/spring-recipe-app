package com.example.springrecipeapp.controllers;

import com.example.springrecipeapp.commands.RecipeCommand;
import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{recipe_id}/ingredients")
    public String showAllRecipes(@PathVariable Long recipe_id, Model model) {
        RecipeCommand recipe = recipeService.findCommandById(recipe_id);
        Recipe recipeMain = recipeService.findById(recipe_id);
        System.out.println(recipe.getIngredients().size());
        System.out.println(recipeMain.getIngredients().size());
        model.addAttribute("recipe", recipe);
        return "recipe/ingredient/list";
    }
}
