package com.zor07.coffeesnob.repository;

import com.zor07.coffeesnob.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
