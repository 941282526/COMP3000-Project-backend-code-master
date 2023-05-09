package com.example.restaurantAssistant.test;

import com.example.restaurantAssistant.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ShopServiceImplTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void save() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllByRecommend() {
    }
}