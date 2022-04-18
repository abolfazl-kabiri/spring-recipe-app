package com.example.springrecipeapp.controllers;

import com.example.springrecipeapp.commands.IngredientCommand;
import com.example.springrecipeapp.services.IngredientService;
import com.example.springrecipeapp.services.RecipeService;
import com.example.springrecipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String initUpdateIngredienteForm(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model) {

        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        model.addAttribute("uomList", unitOfMeasureService.findAll());
        return "/recipe/ingredient/ingredientForm";
    }

//    @PostMapping("/recipe/{recipeId}/ingredient/update")
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String processIngredientForm(@ModelAttribute IngredientCommand ingredient)
    {
        IngredientCommand saved = ingredientService.saveIngredientCommand(ingredient);
        return "redirect:/recipe/" + saved.getRecipeId() + "/ingredient/" +saved.getId() + "/show";
    }
}
