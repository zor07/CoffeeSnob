package com.zor07.coffeesnob.controller;

import com.zor07.coffeesnob.dto.MenuItemDto;
import com.zor07.coffeesnob.dto.ProductDto;
import com.zor07.coffeesnob.dto.UnitDto;
import com.zor07.coffeesnob.entity.MenuItem;
import com.zor07.coffeesnob.service.MenuItemService;
import com.zor07.coffeesnob.service.ProductService;
import com.zor07.coffeesnob.service.UnitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuItemService menuItemService;
    private final ProductService productService;
    private final UnitService unitService;

    public MenuController(MenuItemService menuItemService,
                          ProductService productService,
                          UnitService unitService) {
        this.menuItemService = menuItemService;
        this.productService = productService;
        this.unitService = unitService;
    }


    @GetMapping
    public String menu(Model model) {
        List<MenuItemDto> menuItems = menuItemService.findAll()
                .stream()
                .map(MenuItemDto::toDto)
                .toList();
        model.addAttribute("menuItems", menuItems);
        return "menu/list";
    }

    @GetMapping("/new")
    public String newMenuItem(Model model) {
        addFormDataToModel(model, new MenuItem());
        return "menu/form";
    }

    @GetMapping("/{menuItemId}")
    public String edit(@PathVariable Long menuItemId, Model model) {
        addFormDataToModel(model, menuItemService.findById(menuItemId));
        return "menu/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MenuItemDto menuItem) {
        menuItemService.save(menuItem.toEntity());
        return "redirect:/menu";
    }

    @GetMapping("/{menuItemId}/delete")
    public String delete(@PathVariable Long menuItemId) {
        menuItemService.deleteById(menuItemId);
        return "redirect:/menu";
    }

    private void addFormDataToModel(Model model, MenuItem menuItem) {
        List<ProductDto> products = productService.findAll()
                .stream()
                .map(ProductDto::toDto)
                .toList();

        List<UnitDto> units = unitService.findAll()
                .stream()
                .map(UnitDto::toDto)
                .toList();
        model.addAttribute("products", products);
        model.addAttribute("units", units);
        model.addAttribute("menuItem", menuItem);
    }
}
