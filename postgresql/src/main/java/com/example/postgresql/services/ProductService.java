package com.example.postgresql.services;

import com.example.postgresql.model.Product;
import com.example.postgresql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    public ProductService() {
    }

    @Autowired
    private ProductRepository productRepository;
    public Product createProduct( Product product) {
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        var list =  productRepository.findAll();
        return list;
    }
    public Product getProductById(Long Id) {
      /*  Optional<User> userOptional = userRepository.findById(Id);
        User user = userOptional.get();
        user.getProducts();
        return user;*/
         Optional<Product> productOptional =  productRepository.findById(Id);
           //     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found"));
        Product product = productOptional.get();
        //product.getUser();
         return product;
    }
    public Product deleteById(Long id) {
        Product product=this.productRepository.findById(id).get();
        this.productRepository.deleteById(id);
        return product;
    }
    public Product updateById(@PathVariable Long id, @RequestBody Product product){
        Product productToUpdate=this.productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID does not exist"));;
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        return productRepository.save(productToUpdate);
    }
}
