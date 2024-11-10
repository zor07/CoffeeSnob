package com.zor07.coffeesnob.service;

import com.zor07.coffeesnob.entity.MenuItem;
import com.zor07.coffeesnob.exception.EntityNotFoundException;
import com.zor07.coffeesnob.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> findAll() {
        return menuItemRepository.findAll();
    }

    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("MenuItem with id %s not found", id)));
    }

    public List<MenuItem> findByIds(List<Long> ids) {
        return menuItemRepository.findByIdIn(ids);
    }

    public MenuItem save(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public void deleteById(Long id) {
        menuItemRepository.deleteById(id);
    }
}
