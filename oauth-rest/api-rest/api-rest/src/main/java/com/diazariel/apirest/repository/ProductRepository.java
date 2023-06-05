package com.diazariel.apirest.repository;

import com.diazariel.apirest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
