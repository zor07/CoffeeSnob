package com.zor07.coffeesnob.dto;

import com.zor07.coffeesnob.entity.MenuItem;

import java.math.BigDecimal;
import java.util.Optional;

public record MenuItemDto(
        Long id,
        ProductDto product,
        UnitDto unit,
        BigDecimal quantity,
        BigDecimal price
) {

    public static MenuItemDto toDto(MenuItem menuItem) {
        ProductDto product = Optional.of(menuItem.getProduct())
                .map(ProductDto::toDto)
                .orElse(ProductDto.empty());

        UnitDto unit = Optional.of(menuItem.getUnit())
                .map(UnitDto::toDto)
                .orElse(UnitDto.empty());

        return new MenuItemDto(
                menuItem.getId(),
                product,
                unit,
                menuItem.getQuantity(),
                menuItem.getPrice()
        );
    }

    public MenuItem toEntity() {
        return new MenuItem(
                id,
                product.toEntity(),
                unit.toEntity(),
                quantity,
                price
        );
    }

}
