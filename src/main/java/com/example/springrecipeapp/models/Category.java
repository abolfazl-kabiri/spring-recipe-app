package com.example.springrecipeapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
    
}
