package com.project.web.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.project.web.review.Review;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.OptionalDouble;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String type; // 제품 타입

    @Column(columnDefinition = "TEXT")
    private String name; // 제품 이름
    
    @Column(columnDefinition = "TEXT")
    private String description; // 제품 설명

    @Column()
    private Integer price; // 제품 가격 (BigDecimal 사용)

    @Column()
    private LocalDateTime createDate; // 생성 날짜
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviewList; // 리뷰 리스트
    
    // 평균 별점 계산 메서드
    public double getAvgRating() {
        if (reviewList == null || reviewList.isEmpty()) {
            return 0.0; // 리뷰가 없으면 0점 반환
        }
        
        OptionalDouble average = reviewList.stream()
            .mapToInt(Review::getRating) // 각 리뷰의 별점을 가져옴
            .average();
        
        return average.orElse(0.0); // 평균을 계산하여 반환, 없으면 0.0 반환
    }
}
