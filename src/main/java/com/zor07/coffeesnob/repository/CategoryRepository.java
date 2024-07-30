package com.zor07.coffeesnob.repository;

import com.zor07.coffeesnob.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
