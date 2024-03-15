//package com.example.demo.models;
//
//import jakarta.persistence.*;
//
//import java.util.Calendar;
//import java.util.Objects;
//
////POJO = Plain Object java object
//@Entity
//@Table(name="tblProduct")
//public class Product {
//    //this is prinary key
//    @Id
//    @SequenceGenerator(
//            name ="product_sequence",
//            sequenceName ="product_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "product_sequence"
//    )
//   /* @GeneratedValue(strategy = GenerationType.AUTO)*/
//    private Long id;
//    @Column(nullable = false, unique = true, length = 300)
//    private String productName;
//    private int production_year;
//    private Double price;
//    private String url;
//
//    public Product() {}
//    @Transient
//    private int age;
//    public int getAge() {
//        return Calendar.getInstance().get(Calendar.YEAR) - production_year;
//    }
//    public Product( String productName, int production_year, Double price, String url) {
//
//        this.productName = productName;
//        this.production_year = production_year;
//        this.price = price;
//        this.url = url;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public int getProduction_year() {
//        return production_year;
//    }
//
//    public void setProduction_year(int production_year) {
//        this.production_year = production_year;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", productName='" + productName + '\'' +
//                ", production_year=" + production_year +
//                ", price=" + price +
//                ", url='" + url + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return production_year == product.production_year && age == product.age && Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(price, product.price) && Objects.equals(url, product.url);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, productName, production_year, price, url, age);
//    }
//}
