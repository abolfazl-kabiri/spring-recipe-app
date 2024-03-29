package com.example.springrecipeapp.controllers;

import com.example.springrecipeapp.commands.RecipeCommand;
import com.example.springrecipeapp.exceptions.NotFoundException;
import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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


    @PostMapping("/recipe")
    public String processCreateRecipeForm(@Valid @ModelAttribute("recipe") RecipeCommand recipe, BindingResult result) {

        if (result.hasErrors()) {
            return "/recipe/recipeForm";
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipe);
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "404error";
    }



}
