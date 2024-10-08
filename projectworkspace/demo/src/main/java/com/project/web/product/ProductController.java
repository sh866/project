package com.project.web.product;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
@RequestMapping("/product")
@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductService productService;
    
    // 제품 목록 페이지
    @GetMapping("/list")
    public String list(Model model) {
        List<Product> productList = this.productService.getList();
        model.addAttribute("productList", productList);
        return "product_list"; // 템플릿 파일명
    }
    
    // 제품 상세 페이지 (평균 별점 포함)
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        // 평균 별점 계산된 제품 정보 가져오기
        Product product = this.productService.getProductWithAvgRating(id);
        model.addAttribute("product", product);
        return "product_detail"; // 템플릿 파일명
    }
}
