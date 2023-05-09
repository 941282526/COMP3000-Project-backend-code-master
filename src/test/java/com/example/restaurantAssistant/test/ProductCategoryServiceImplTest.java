package com.example.restaurantAssistant.test;

import com.example.restaurantAssistant.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void save() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findByShopIdAndStatus() {
    }
}