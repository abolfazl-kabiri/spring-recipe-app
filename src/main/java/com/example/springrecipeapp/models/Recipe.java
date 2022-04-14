package com.example.springrecipeapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
//@EqualsAndHashCode(exclude = {"ingredients", "categories"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Lob
    private String description;
    private Integer cookTime;
    private Integer prepTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Lob
    private byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany(mappedBy = "recipes")
    private Set<Ingredient> ingredients;

    @ManyToMany(mappedBy = "recipes")
    private Set<Category> categories;

}
