package com.zor07.coffeesnob.dto;


import com.zor07.coffeesnob.entity.Unit;

public record UnitDto(Long id, String name) {

    public static UnitDto toDto(Unit unit) {
        return new UnitDto(unit.getId(), unit.getName());
    }

    public static UnitDto empty() {
        return new UnitDto(null, null);
    }

    public Unit toEntity() {
        return new Unit(id, name);
    }


}
