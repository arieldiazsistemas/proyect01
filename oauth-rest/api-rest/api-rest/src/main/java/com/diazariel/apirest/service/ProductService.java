package com.diazariel.apirest.service;

import com.diazariel.apirest.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> listAll();

    public void save(Product product);

    public Product get(Long id);

    public void delete(Long id);
}
