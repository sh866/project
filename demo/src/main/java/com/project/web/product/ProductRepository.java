package com.project.web.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByType(String Type);
    Product findByTypeAndName(String Type, String Name);
    List<Product> findByTypeLike(String Type);
    Page<Product> findAll(Pageable pageable);
}