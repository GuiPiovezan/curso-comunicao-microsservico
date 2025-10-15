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
@Table(name = "PRODUCT")
public class Product {

    @Id
    @SequenceGenerator(
        name = "product_seq",
        sequenceName = "product_seq",
        allocationSize = 1,
        initialValue = 4
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_category", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @ManyToOne
    @JoinColumn(name = "fk_supplier", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_supplier"))
    private Supplier supplier;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;
}
