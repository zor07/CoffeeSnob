package com.zor07.coffeesnob.controller;

import com.zor07.coffeesnob.dto.ProductDto;
import com.zor07.coffeesnob.entity.Product;
import com.zor07.coffeesnob.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String allProducts(Model model) {
        List<ProductDto> products = productService.findAll()
                .stream()
                .map(ProductDto::fromProduct)
                .toList();

        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", ProductDto.fromProduct(new Product()));
        return "product/form";
    }

    @GetMapping("/{productId}")
    public String edit(@PathVariable Long productId, Model model) {
        ProductDto product = ProductDto.fromProduct(productService.findById(productId));
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
