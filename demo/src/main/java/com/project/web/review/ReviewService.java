package com.project.web.review;


import com.project.web.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor  // final 필드를 위한 생성자 자동 생성
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // 세 개의 파라미터 (Product, content, rating)를 받도록 수정
    public void create(Product product, String content, int rating) {
        Review review = new Review();
        review.setContent(content);  // 리뷰 내용 설정
        review.setCreateDate(LocalDateTime.now());  // 리뷰 작성 시간 설정
        review.setProduct(product);  // 해당 제품 설정
        review.setRating(rating);    // 별점 설정
        this.reviewRepository.save(review);  // 리뷰 저장
    }
}