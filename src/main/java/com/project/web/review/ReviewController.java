package com.project.web.review;

import com.project.web.product.Product;
import com.project.web.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/review")
@RequiredArgsConstructor
@Controller
public class ReviewController {
    
    private final ProductService productService;
    private final ReviewService reviewService;

    @PostMapping("/create/{id}")
    public String createReview(Model model, @PathVariable("id") Integer id, 
                               @RequestParam("content") String content, 
                               @RequestParam("rating") int rating) throws Throwable {
        // 제품 정보 가져오기
        Product product = this.productService.getProduct(id);
        
        // 리뷰 생성
        this.reviewService.create(product, content, rating);
        
        // 제품 상세 페이지로 리디렉션
        return String.format("redirect:/product/detail/%s", id);
    }
}