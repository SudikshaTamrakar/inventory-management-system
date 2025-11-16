package com.example.Inventory_Management.controller;

import com.example.Inventory_Management.entity.ConfirmationForm;
import com.example.Inventory_Management.entity.Product;
import com.example.Inventory_Management.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.Random;
@Controller

public class ProductController {
@Autowired
    private ProductRepo productRepo;


    // display the html page
    @GetMapping("/")
    public String getIndex(Model model) {
        List<Product> productList = productRepo.findAll();
        model.addAttribute("products", productList);
        model.addAttribute("product", new Product());
        model.addAttribute("confirmationForm", new ConfirmationForm());
        return "index";
    }

    // Insert employee data
    @PostMapping("/create")
    public String newProduct(Product product, Model model) {
        System.out.println("Saving record...");
        model.addAttribute("product", new Product());

        // creating dynamic Employee ID

        String productId = "Product" ;
        Random random = new Random();
        long randomNumber = 1000 + random.nextInt(9000);
        productId = productId + randomNumber;
        product.setId(productId);

        // save the employee
        productRepo.save(product);
        System.out.println("Saved Successfully");
        return "redirect:/index";
    }

    // update the existing employee
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", new Product());
        Optional<Product> existingProduct = productRepo.findById(product.getId());

        // checking employee exist or not
        if (existingProduct.isPresent()) {
            productRepo.save(product);
        } else {
            model.addAttribute("errorMessage", "Product with ID " + product.getId() + " not found.");
        }
        return "redirect:/index";
    }

    // delete an employee by id
    @PostMapping("/remove")
    public String removeProduct(Product product, Model model) {
        model.addAttribute("product", new Product());
        Optional<Product> existingProduct = productRepo.findById(product.getId());
        if (existingProduct.isPresent()) {
            productRepo.deleteById(product.getId());
        }
        return "redirect:/index";
    }

    // delete all employees data by confromation
    @PostMapping("/remove/all")
    public String removeAll(@ModelAttribute ConfirmationForm confirmationForm, Model model) {
        String confirmation = confirmationForm.getConfirmation();
        if ("Yes".equalsIgnoreCase(confirmation)) {
            productRepo.deleteAll();
        } else {
            return "redirect:/index";
        }
        return "redirect:/index";
    }

}
