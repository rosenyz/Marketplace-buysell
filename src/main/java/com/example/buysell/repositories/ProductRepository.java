package com.example.buysell.repositories;

import com.example.buysell.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
}
