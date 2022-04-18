package com.example.springrecipeapp.services;

import com.example.springrecipeapp.commands.UnitOfMeasureCommand;
import com.example.springrecipeapp.models.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {

    void delete(UnitOfMeasure uom);

    UnitOfMeasure save(UnitOfMeasure uom);

    void deleteById(Long id);

    UnitOfMeasure findByDescription(String description);

    UnitOfMeasure findById(Long id);

    Set<UnitOfMeasureCommand> findAll();
}
