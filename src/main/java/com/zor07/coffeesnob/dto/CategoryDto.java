package com.zor07.coffeesnob.dto;

import com.zor07.coffeesnob.entity.Category;

public record CategoryDto(Long id, String name) {

    public static CategoryDto from(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public static CategoryDto empty() {
        return new CategoryDto(null, null);
    }

    public Category toEntity() {
        return new Category(id, name);
    }


}
