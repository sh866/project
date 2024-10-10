package com.project.web;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.web.product.Product;
import com.project.web.product.ProductRepository;


@SpringBootTest
class DemoApplicationTests {
	
    @Autowired
    private ProductRepository productRepository;
    
    @Transactional

	@Test
	void testJpa() {
        List<Product> all = this.productRepository.findAll();
        assertEquals(2, all.size());

        Product p = all.get(0);
        assertEquals("튀김소보루", p.getName());
	}

}
