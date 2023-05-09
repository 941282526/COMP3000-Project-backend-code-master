package com.example.restaurantAssistant.service.impl;

import com.example.restaurantAssistant.pojo.ShopCategory;
import com.example.restaurantAssistant.repository.ShopCategoryRepository;
import com.example.restaurantAssistant.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryRepository repository;

    @Override
    public ShopCategory save(ShopCategory shopCategory) {
        return repository.save(shopCategory);
    }

    @Override
    public ShopCategory findOne(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<ShopCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ShopCategory> findAllByState(Integer state) {
        return repository.findAllByState(state);
    }
}
