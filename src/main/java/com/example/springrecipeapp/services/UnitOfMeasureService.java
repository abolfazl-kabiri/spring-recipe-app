package com.example.springrecipeapp.services;

import com.example.springrecipeapp.models.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {

    void delete(UnitOfMeasure uom);

    UnitOfMeasure save(UnitOfMeasure uom);

    void deleteByDescription(String uom);

    UnitOfMeasure findByDescription(String uom);

    Set<UnitOfMeasure> findAll();
}
