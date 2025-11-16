package com.example.Inventory_Management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "inventory")
public class Product {
@Id
private String id;
    private String name;
    private Number quantity;
    private Double price;

}
