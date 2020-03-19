package com.naitcherif.mycatalog;

import com.naitcherif.mycatalog.entities.Product;
import com.naitcherif.mycatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class MyCatalogApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyCatalogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null, "Samsung S10", 10000, 20));
        productRepository.save(new Product(null, "HP VH5", 14000, 10));
        productRepository.save(new Product(null, "MI Band", 200, 15));
        Page<Product> products = productRepository.findAll(PageRequest.of(0, 10));
        System.out.println(products.getTotalElements());
//        products.forEach(p -> {
//            System.out.println(p.toString());
//        });
//        Page<Product> searchProducts = productRepository.search("", 10000.0, PageRequest.of(0, 10));
//        System.out.println(searchProducts.getTotalElements());
//        searchProducts.forEach(p -> {
//            System.out.println(p.toString());
//        });
    }
}
