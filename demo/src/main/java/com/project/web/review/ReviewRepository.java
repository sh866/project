package com.project.web.review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.web.product.Product;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 제품에 대한 모든 리뷰 가져오기
    List<Review> findByProduct(Product product);

    // 특정 제품에 대한 페이징된 리뷰 가져오기
    Page<Review> findByProductOrderByCreateDateDesc(Product product, Pageable pageable); 
    
    Page<Review> findByProduct(Product product, Pageable pageable);
    
}
