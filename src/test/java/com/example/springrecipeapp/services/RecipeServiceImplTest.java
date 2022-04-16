package com.example.springrecipeapp.services;

import com.example.springrecipeapp.models.Recipe;
import com.example.springrecipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    RecipeRepository repository;

    @InjectMocks
    RecipeServiceImpl service;

    Set<Recipe> recipes;
    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipes = new HashSet<>();
        recipe = new Recipe();
        recipes.add(recipe);
    }

    @Test
    void delete() {

        service.delete(recipe);
        assertNull(service.findById(recipe.getId()));
    }


    @Test
    void getRecipes() {

        when(repository.findAll()).thenReturn(recipes);
        Set<Recipe> r = service.getRecipes();
        assertEquals(1, r.size());
        verify(repository).findAll();
    }


}