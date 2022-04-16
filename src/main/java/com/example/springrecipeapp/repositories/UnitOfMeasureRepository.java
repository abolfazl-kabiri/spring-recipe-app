package com.example.springrecipeapp.repositories;

import com.example.springrecipeapp.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    void deleteByDescription(String uom);

    Optional<UnitOfMeasure> findByDescription(String uom);
}
