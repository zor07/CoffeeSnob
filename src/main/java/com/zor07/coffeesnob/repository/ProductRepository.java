package com.zor07.coffeesnob.repository;

import com.zor07.coffeesnob.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
