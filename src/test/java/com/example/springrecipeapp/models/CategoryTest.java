package com.example.springrecipeapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setCategoryName("american");
    }

    @Test
    void getId() {
        assertNotNull(category.getId());
        assertEquals(1L, category.getId());
    }

    @Test
    void getCategoryName() {
        assertNotNull(category.getCategoryName());
        assertEquals("american", category.getCategoryName());
    }

    @Test
    void setId() {
        category.setId(2L);
        assertEquals(2L, category.getId());
    }
}