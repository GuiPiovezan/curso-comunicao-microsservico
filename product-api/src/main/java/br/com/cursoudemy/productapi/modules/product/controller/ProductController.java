package br.com.cursoudemy.productapi.modules.product.controller;

import br.com.cursoudemy.productapi.modules.product.dto.ProductRequestDto;
import br.com.cursoudemy.productapi.modules.product.dto.ProductResponseDto;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponseDto save(@RequestBody ProductRequestDto request) {
        return productService.save(request);
    }
}
