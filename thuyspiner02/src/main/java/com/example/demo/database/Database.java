//package com.example.demo.database;
//
//import com.example.demo.models.Product;
//import com.example.demo.repositories.ProductRepository;
//import jakarta.persistence.Entity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class Database {
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//
//    @Bean
//    CommandLineRunner initDatabase(ProductRepository productRepository) {
//
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//              /*  Product productA = new Product("SanPham A: ", 2022, 100.0, "http://example.com/productA");
//                Product productB = new Product("SanPham B:", 2023, 150.0, "http://example.com/productB");
//                logger.info("insert data: "+productRepository.save(productA));
//                logger.info("insert data: "+productRepository.save(productB));*/
//            }
//        };
//    }
//}
