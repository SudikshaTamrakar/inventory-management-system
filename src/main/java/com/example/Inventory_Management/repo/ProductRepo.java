package com.example.Inventory_Management.repo;

import com.example.Inventory_Management.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product,String> {
}
