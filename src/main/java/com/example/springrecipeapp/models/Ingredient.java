package com.example.springrecipeapp.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @OneToOne
    private UnitOfMeasure unitOfMeasure;

    @ManyToMany
    @JoinTable(name = "recipe_ingredient",
    joinColumns = @JoinColumn(name = "ingredient_id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes = new HashSet<>();


    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Set<Recipe> recipes) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = uom;
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

}
