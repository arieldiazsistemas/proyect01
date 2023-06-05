package com.diazariel.apirest.controller;

import com.diazariel.apirest.dto.ProductDTO;
import com.diazariel.apirest.entity.Product;
import com.diazariel.apirest.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class AppController {

    @Autowired
    private ProductService service;

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getListProduct() {
        logger.info("get all product");
        List<Product> listProducts = service.listAll();
        return ResponseEntity.ok(listProducts);
    }

    @PostMapping("/new")
    public ResponseEntity<Product> newProduct(@RequestBody ProductDTO productDTO) {
        Product product =  new Product();
        product.setBrand(productDTO.getBrand());
        product.setMadein(productDTO.getMadein());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        logger.info("save product {}",productDTO.toString());
        service.save(product);
        return ResponseEntity.ok(product);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> showEditProductForm(@PathVariable(name = "id") Long id ,
                                                       @RequestBody ProductDTO productDTO) {
        try {
            Product product = service.get(id);
            product.setBrand(productDTO.getBrand());
            product.setMadein(productDTO.getMadein());
            product.setPrice(productDTO.getPrice());
            product.setName(productDTO.getName());
            logger.info("update product {}",productDTO.toString());
            service.save(product);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id) {
       try {
           service.delete(id);
           logger.info("product delete");
           return ResponseEntity.status(HttpStatus.OK).body("Deleted Product");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id) {
        try {
            Product product = service.get(id);
            logger.info("get product {}",product.toString());
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);//TODO en tu front de preguntar por el body si es null mostrar msj q no exites producto
        }
    }
}
