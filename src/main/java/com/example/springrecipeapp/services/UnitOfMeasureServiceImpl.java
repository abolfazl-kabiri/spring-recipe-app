package com.example.springrecipeapp.services;

import com.example.springrecipeapp.models.UnitOfMeasure;
import com.example.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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
    public void deleteByUnitOfMeasure(String uom) {
        unitOfMeasureRepository.deleteByUnitOfMeasure(uom);
    }

    @Override
    public UnitOfMeasure findByUnitOfMeasure(String uom) {
        return unitOfMeasureRepository.findByUnitOfMeasure(uom).orElse(null);
    }

    @Override
    public Set<UnitOfMeasure> findAll() {

        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(unitOfMeasures::add);
        return unitOfMeasures;
    }
}
