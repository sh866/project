package com.project.web.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.project.web.DataNotFoundException;
import com.project.web.review.Review;
import com.project.web.review.ReviewRepository;

@RequiredArgsConstructor
@Service
public class ProductService  {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;  // 리뷰 레포지토리 추가

    public List<Product> getList() {
        return this.productRepository.findAll();
    }
    
    public Product getProduct(Integer id) {  
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new DataNotFoundException("product not found");
        }
    }
    
    
    // 최신순으로 페이징된 리뷰를 가져오는 메서드
    public Page<Review> getPagedReviews(Integer productId, int page, int size) {
        Product product = this.getProduct(productId);  // 제품 정보 가져오기
        Pageable pageable = PageRequest.of(page, size, Sort.by("createDate").descending());  // 최신순 정렬 추가
        return reviewRepository.findByProduct(product, pageable);  // 페이징된 리뷰 반환
    }
    public Product getProductWithAvgRating(Integer id) {  
        Product product = this.getProduct(id);  // 기존 메서드로 제품을 가져옴

        if (product == null) {
            throw new DataNotFoundException("Product not found");
        }

        // 평균 별점 계산
        String avgRating = product.getAvgRating();
        
        return product;
    }

}