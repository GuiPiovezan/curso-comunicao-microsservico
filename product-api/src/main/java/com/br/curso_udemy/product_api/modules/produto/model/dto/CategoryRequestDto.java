package com.br.curso_udemy.product_api.modules.produto.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @JsonProperty("description")
    private String description;
}
