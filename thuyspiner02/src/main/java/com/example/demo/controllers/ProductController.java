//package com.example.demo.controllers;
//
//import com.example.demo.models.Product;
//import com.example.demo.models.ResponseObject;
//import com.example.demo.repositories.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path="api/v1/Products")
//public class ProductController {
//    @Autowired
//    private ProductRepository repository;
//
//    @GetMapping("")
//        //http://localhost:8888/api/v1/Products
//    List<Product> getAllProducts() {
//
//        return repository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
//        Optional<Product> foundProduct = repository.findById(id);
//        return foundProduct.isPresent() ?
//                ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("ok", "Query product successfully", foundProduct)
//                        //foundProduct == data
//                ):
//        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                new ResponseObject("false", "Cannot find product with id = "+id, "")
//        );
//    }
//    //insert(Post)
//    //postman: raw , json
//    @PostMapping("/insert")
//    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
//        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
//        if(foundProducts.size()>0) {
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new ResponseObject("failed", "Đã bị trùng tên", "")
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//               new ResponseObject("ok", "Insert Product successfully", repository.save(newProduct))
//        );
//    }
//    //update, upsert = thêm mà k tìm cái để thêm thì insert
//    @PutMapping("/{id}")
//    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
//        //k dùng optional cho product vì nó trả về 1 đối tượng
//        Product updateProduct = repository.findById(id)
//                .map(product -> {
//                    product.setProductName(newProduct.getProductName());
//                    product.setProduction_year(newProduct.getProduction_year());
//                    product.setPrice(newProduct.getPrice());
//                    return repository.save(product);
//                }).orElseGet(() ->{
//                    newProduct.setId(id);
//                    return repository.save(newProduct);
//                });
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Update Product successfully", updateProduct)
//        );
//    }
//    //delete
//    @DeleteMapping("/{id}")
//    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
//        boolean exists = repository.existsById(id);
//        if (exists) {
//            repository.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Delete Product successfully", "")
//            );
//            }
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("false", "Cannot find product to delete", "")
//            );
//    }
//}
//
