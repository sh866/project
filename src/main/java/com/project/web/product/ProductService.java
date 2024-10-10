package com.project.web.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.project.web.DataNotFoundException;
import com.project.web.review.Review;
import com.project.web.review.ReviewRepository;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;  // 리뷰 레포지토리 추가

    // 제품 목록 가져오기
    public List<Product> getList() {
        List<Product> productList = this.productRepository.findAll();

        // 제품 이름에 따라 이미지 경로 설정
        for (Product product : productList) {
            switch (product.getName()) {
                case "튀소구마":
                    product.setImageUrl("/튀소구마.png");
                    break;
                case "부추세트":
                    product.setImageUrl("/부추빵.png");
                    break;
                case "해바라기":
                    product.setImageUrl("/해바라기.png");
                    break;
                case "스테이크빵":
                    product.setImageUrl("/스테이크빵.png");
                    break;
                case "소금크롸상":
                    product.setImageUrl("/소금크롸상.png");
                    break;
                case "크림치즈화이트번":
                    product.setImageUrl("/크림치즈화이트번.png");
                    break;
                case "과일생케익 2호":
                    product.setImageUrl("/과일생케익.png");
                    break;
                case "티라미수롤":
                    product.setImageUrl("/티라미수롤.png");
                    break;
                case "요거트롤":
                    product.setImageUrl("/요거트롤.png");
                    break;
                default:
                    product.setImageUrl("/default.png");  // 기본 이미지 설정
            }
        }
        return productList;
    }

    // 제품 ID로 제품 조회
    public Product getProduct(Integer id) {  
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new DataNotFoundException("Product not found");
        }
    }

    // 페이징된 리뷰를 가져오는 메서드
    public Page<Review> getPagedReviews(Integer productId, Pageable pageable) {
        Product product = this.getProduct(productId);  // 제품 정보 가져오기
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
