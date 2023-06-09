package com.example.restaurantAssistant.repository;

import com.example.restaurantAssistant.pojo.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopCategoryRepository extends JpaRepository<ShopCategory,Integer> {

    List<ShopCategory> findAllByState(Integer state);


}
