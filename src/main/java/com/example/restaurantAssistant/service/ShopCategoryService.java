package com.example.restaurantAssistant.service;

import com.example.restaurantAssistant.pojo.ShopCategory;

import java.util.List;

public interface ShopCategoryService {


    ShopCategory save(ShopCategory shopCategory);


    ShopCategory findOne(Integer id);


    List<ShopCategory> findAll();


    List<ShopCategory> findAllByState(Integer state);



}
