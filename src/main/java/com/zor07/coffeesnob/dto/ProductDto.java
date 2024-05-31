package com.zor07.coffeesnob.dto;

import com.zor07.coffeesnob.entity.Product;

public record ProductDto(Long id, String name, String description) {

    public static ProductDto fromProduct(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription());
    }

    public Product toEntity() {
        return new Product(id, name, description);
    }

}
