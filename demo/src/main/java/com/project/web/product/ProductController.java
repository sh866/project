package com.project.web.product;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

import com.project.web.DataNotFoundException;
import com.project.web.review.Review;

@RequestMapping("/product")
@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductService productService;
    
    @GetMapping("/list")
    public String list(Model model) {
        List<Product> productList = this.productService.getList();
        model.addAttribute("productList", productList);
        return "product_list"; // 템플릿 파일명
    }
    
 // 제품 상세 페이지 (리뷰 페이징 포함)
    @GetMapping("/detail/{id}")
    public String detail(Model model, 
                         @PathVariable("id") Integer id,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "5") int size) {
        // 제품 정보 가져오기
        Product product = this.productService.getProductWithAvgRating(id);

        if (product == null) {
            throw new DataNotFoundException("Product not found");
        }

        // 페이징된 리뷰 가져오기
//        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviewPage = this.productService.getPagedReviews(id, page, size);

        model.addAttribute("product", product);  // product 객체 추가
        model.addAttribute("reviewPage", reviewPage);  // reviewPage 추가
        return "product_detail";
    }
    
}

