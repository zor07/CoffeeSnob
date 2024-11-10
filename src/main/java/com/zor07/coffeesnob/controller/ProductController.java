package com.zor07.coffeesnob.controller;

import com.zor07.coffeesnob.dto.CategoryDto;
import com.zor07.coffeesnob.dto.ProductDto;
import com.zor07.coffeesnob.entity.Product;
import com.zor07.coffeesnob.service.CategoryService;
import com.zor07.coffeesnob.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping
    public String allProducts(Model model) {
        List<ProductDto> products = productService.findAll()
                .stream()
                .map(ProductDto::toDto)
                .toList();

        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        List<CategoryDto> categories = categoryService.findAll()
                .stream()
                .map(CategoryDto::from)
                .toList();
        model.addAttribute("categories", categories);

        model.addAttribute("product", ProductDto.toDto(new Product()));
        return "product/form";
    }

    @GetMapping("/{productId}")
    public String edit(@PathVariable Long productId, Model model) {
        List<CategoryDto> categories = categoryService.findAll()
                .stream()
                .map(CategoryDto::from)
                .toList();
        model.addAttribute("categories", categories);
        ProductDto product = ProductDto.toDto(productService.findById(productId));
        model.addAttribute("product", product);
        return "product/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ProductDto productDto) {
        productService.save(productDto.toEntity());
        return "redirect:/product";
    }

    @GetMapping("/{productId}/delete")
    public String delete(@PathVariable Long productId) {
        productService.deleteById(productId);
        return "redirect:/product";
    }
}
