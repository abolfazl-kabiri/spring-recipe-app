package com.example.springrecipeapp.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Table(name = "notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String notes;

    @OneToOne
    private Recipe recipe;
}
