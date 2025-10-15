package com.br.curso_udemy.product_api.modules.produto.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
    @SequenceGenerator(
        name = "category_seq_gen",
        sequenceName = "category_seq",
        allocationSize = 1,
        initialValue = 4
    )
    private Integer id;

    @Column(name = "description", nullable = false, length = 100)
    private String description;
}
