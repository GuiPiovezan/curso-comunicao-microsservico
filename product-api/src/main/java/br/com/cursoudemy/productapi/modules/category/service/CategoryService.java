package br.com.cursoudemy.productapi.modules.category.service;

import br.com.cursoudemy.productapi.config.exception.ValidateException;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryRequestDto;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryResponseDto;
import br.com.cursoudemy.productapi.modules.category.repository.CategoryRepository;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ValidateException("There's no category for the given id."));
    }
    public List<CategoryResponseDto> findByDecription(String description) {
        if(isEmpty(description)) {
            throw new ValidateException("The category description must be informed.");
        }

        return categoryRepository
                .findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(CategoryResponseDto::of)
                .collect(Collectors.toList());
    }

    public List<CategoryResponseDto> findAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(CategoryResponseDto::of)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto findByIdResponse(Integer categoryId) {
        return CategoryResponseDto.of(findById(categoryId));
    }

    public CategoryResponseDto save(CategoryRequestDto request) {
        validateCategoryNameInformed(request);
        var category = categoryRepository.save(Category.of(request));
        return CategoryResponseDto.of(category);
    }

    private void validateCategoryNameInformed(CategoryRequestDto request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidateException("The category description was not informed.");
        }
    }
}
