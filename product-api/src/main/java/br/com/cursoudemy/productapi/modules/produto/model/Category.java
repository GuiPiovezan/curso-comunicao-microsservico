package br.com.cursoudemy.productapi.modules.produto.model;

import javax.persistence.*;

import br.com.cursoudemy.productapi.modules.produto.dto.CategoryRequestDto;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public static Category of(CategoryRequestDto request) {
        var category = new Category();
        BeanUtils.copyProperties(request, category);

        return category;
    }
}
