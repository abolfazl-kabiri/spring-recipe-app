package com.example.springrecipeapp.controllers;

import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{recipe_id}")
    public String showRecipe(@PathVariable Long recipe_id, Model model) {

        Recipe recipe = recipeService.findById(recipe_id);
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }
}
