package com.example.springrecipeapp.controllers;

import com.example.springrecipeapp.commands.RecipeCommand;
import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/recipe/new")
    public String initCreateRecipeForm(Model model) {
        RecipeCommand recipe = new RecipeCommand();
        model.addAttribute("recipe", recipe);
        return "recipe/recipeForm";
    }


    @PostMapping("/recipe/new")
    public String processCreateRecipeForm(@ModelAttribute RecipeCommand recipe, BindingResult result) {

        if (result.hasErrors()) {
            throw new RuntimeException("binding result has errors");
        }

        RecipeCommand savedCommand = recipeService.saveCommand(recipe);
        return "redirect:/recipe/show/" + savedCommand.getId();
    }


    @GetMapping("/recipe/update/{recipe_id}")
    public String initUpdateRecipeForm(@PathVariable Long recipe_id, Model model) {
        RecipeCommand recipe = recipeService.findCommandById(recipe_id);
        model.addAttribute("recipe", recipe);
        return "recipe/recipeForm";

    }

    @GetMapping("/recipe/delete/{recipe_id}")
    public String deleteRecipe(@PathVariable Long recipe_id) {
        recipeService.deleteById(recipe_id);
        return "redirect:/";
    }


}
