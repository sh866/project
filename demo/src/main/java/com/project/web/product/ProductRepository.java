package com.project.web.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByType(String Type);
    Product findByTypeAndName(String Type, String Name);
    List<Product> findByTypeLike(String Type);
}