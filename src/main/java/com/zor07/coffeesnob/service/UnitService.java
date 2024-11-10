package com.zor07.coffeesnob.service;

import com.zor07.coffeesnob.entity.Unit;
import com.zor07.coffeesnob.exception.EntityNotFoundException;
import com.zor07.coffeesnob.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    public Unit findById(Long id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Unit with id %s not found", id)));
    }

    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }

    public void deleteById(Long id) {
        unitRepository.deleteById(id);
    }
}
