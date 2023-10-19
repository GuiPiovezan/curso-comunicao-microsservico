package br.com.cursoudemy.productapi.modules.produto.controller;

import br.com.cursoudemy.productapi.modules.produto.dto.CategoryRequestDto;
import br.com.cursoudemy.productapi.modules.produto.dto.CategoryResponseDto;
import br.com.cursoudemy.productapi.modules.produto.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto request) {
        return categoryService.save(request);
    }
}
