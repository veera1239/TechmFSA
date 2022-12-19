package com.veera;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veera.ResourceNotFoundException;
import com.veera.Product;
import com.veera.ProductRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/techm/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // get all product
    @GetMapping("/product")
    public List < Product > getAllProducts() {
        return productRepository.findAll();
    }

    // create product rest api
    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // get product by id rest api
    @GetMapping("/product/{id}")
    public ResponseEntity < Product > getProductById(@PathVariable String id) {
        Product product = productRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Product not exist with id :" + id));
        return ResponseEntity.ok(product);
    }

    // update product rest api

    @PutMapping("/product/{id}")
    public ResponseEntity < Product > updateProduct(@PathVariable String id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Product not exist with id :" + id));

      //  product.setId(productDetails.getId());
        product.setpName(productDetails.getpName());
        product.setLoc(productDetails.getLoc());

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
    
   

    // delete product rest api
    @DeleteMapping("/product/{id}")
    public ResponseEntity < Map < String, Boolean >> deleteProduct(@PathVariable String id) {
    	Product product = productRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Product not exist with id :" + id));

    	productRepository.delete(product);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}