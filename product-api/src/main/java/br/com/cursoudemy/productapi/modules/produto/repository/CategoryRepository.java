package br.com.cursoudemy.productapi.modules.produto.repository;

import br.com.cursoudemy.productapi.modules.produto.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}