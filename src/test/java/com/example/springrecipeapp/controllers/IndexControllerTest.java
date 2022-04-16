package com.example.springrecipeapp.controllers;

import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    IndexController controller;

    MockMvc mockMvc;
    Set<Recipe> recipes;


    @BeforeEach
    void setUp() {

        recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void showIndexPage() throws Exception {

        when(recipeService.getRecipes()).thenReturn(recipes);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("recipes", hasSize(1)));

        verify(recipeService).getRecipes();
    }


}