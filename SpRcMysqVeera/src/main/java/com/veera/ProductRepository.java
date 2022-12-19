package com.veera;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.veera.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}