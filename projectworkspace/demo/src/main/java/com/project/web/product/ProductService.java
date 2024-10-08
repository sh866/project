package com.project.web.product;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import com.project.web.DataNotFoundException;


@RequiredArgsConstructor
@Service
public class ProductService  {

    private final ProductRepository productRepository;

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

    // 평균 별점을 계산하여 반환하는 메서드
    public Product getProductWithAvgRating(Integer id) {  
        Product product = this.getProduct(id);  // 기존 메서드로 제품을 가져옴
        
        // 평균 별점 계산
        double avgRating = product.getAvgRating();
        
        // 여기서 product 객체를 반환 (필요시 avgRating을 별도로 처리할 수 있음)
        return product;
    }
}