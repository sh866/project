package com.project.web.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.web.product.Product;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduct(Product product);
}