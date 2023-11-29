package br.com.cursoudemy.productapi.modules.category.controller;

import br.com.cursoudemy.productapi.modules.category.dto.CategoryRequestDto;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryResponseDto;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseDto save(@RequestBody CategoryRequestDto request) {
        return categoryService.save(request);
    }

    @GetMapping
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    public CategoryResponseDto findById(@PathVariable Integer id) {
        return categoryService.findByIdResponse(id);
    }

    @GetMapping("description/{description}")
    public List<CategoryResponseDto> findByDescription(@PathVariable String description) {
        return categoryService.findByDecription(description);
    }
}
