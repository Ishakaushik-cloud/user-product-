package com.example.postgresql.controller;
import jakarta.validation.Valid;
import com.example.postgresql.model.Product;
import com.example.postgresql.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private  ProductService productService;

    @GetMapping
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("{id}")
    public ResponseEntity  getOneProduct(@PathVariable  Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid Product product){
        return ResponseEntity.status(201).body(productService.createProduct(product));

    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteById(id));
    }
    @PutMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable Long id,@RequestBody Product product){
        return ResponseEntity.ok(productService.updateById(id,product));
    }
}

