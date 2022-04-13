package com.example.springrecipeapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notes;

    @OneToOne
    private Recipe recipe;

}
