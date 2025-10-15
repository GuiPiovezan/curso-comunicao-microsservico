package com.br.curso_udemy.product_api.modules.produto.controller;

import com.br.curso_udemy.product_api.modules.produto.model.dto.CategoryRequestDto;
import com.br.curso_udemy.product_api.modules.produto.model.dto.CategoryResponseDto;
import com.br.curso_udemy.product_api.modules.produto.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto request) {
        return categoryService.save(request);
    }
}
