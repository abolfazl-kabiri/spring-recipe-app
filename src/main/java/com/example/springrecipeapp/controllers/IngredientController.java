package com.example.springrecipeapp.controllers;

import com.example.springrecipeapp.commands.IngredientCommand;
import com.example.springrecipeapp.commands.RecipeCommand;
import com.example.springrecipeapp.services.IngredientService;
import com.example.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String showAllIngredients(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showAllIngredients(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {

        IngredientCommand ingredient = ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId);
        model.addAttribute("ingredient", ingredient);

        return "/recipe/ingredient/show";
    }
}
