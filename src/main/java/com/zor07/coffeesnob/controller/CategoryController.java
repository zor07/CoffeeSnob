package com.zor07.coffeesnob.controller;

import com.zor07.coffeesnob.dto.CategoryDto;
import com.zor07.coffeesnob.entity.Category;
import com.zor07.coffeesnob.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController( CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String allCategories(Model model) {
        List<CategoryDto> categories = categoryService.findAll()
                .stream()
                .map(CategoryDto::from)
                .toList();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        model.addAttribute("category", CategoryDto.from(new Category()));
        return "category/form";
    }

    @GetMapping("/{categoryId}")
    public String edit(@PathVariable Long categoryId, Model model) {
        CategoryDto category = CategoryDto.from(categoryService.findById(categoryId));
        model.addAttribute("category", category);
        return "category/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute CategoryDto category) {
        categoryService.save(category.toEntity());
        return "redirect:/category";
    }

    @GetMapping("/{categoryId}/delete")
    public String delete(@PathVariable Long categoryId) {
        categoryService.deleteById(categoryId);
        return "redirect:/category";
    }
}
