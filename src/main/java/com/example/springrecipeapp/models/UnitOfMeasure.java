package com.example.springrecipeapp.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "UOMs")
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

}
