package com.example.springrecipeapp.services;

import com.example.springrecipeapp.commands.UnitOfMeasureCommand;
import com.example.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.springrecipeapp.models.UnitOfMeasure;
import com.example.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public void delete(UnitOfMeasure uom) {
        unitOfMeasureRepository.delete(uom);
    }

    @Override
    public UnitOfMeasure save(UnitOfMeasure uom) {
        return unitOfMeasureRepository.save(uom);
    }

    @Override
    public void deleteById(Long id) {
        unitOfMeasureRepository.deleteById(id);
    }

    @Override
    public UnitOfMeasure findByDescription(String description) {
        return unitOfMeasureRepository.findByDescription(description).orElse(null);
    }

    @Override
    public Set<UnitOfMeasureCommand> findAll() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                        .spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toSet());

    }

    @Override
    public UnitOfMeasure findById(Long id) {
        return unitOfMeasureRepository.findById(id).orElse(null);
    }
}
