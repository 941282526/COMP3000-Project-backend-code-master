package com.example.restaurantAssistant.service.impl;

import com.example.restaurantAssistant.enums.ResultEnum;
import com.example.restaurantAssistant.enums.StateEnum;
import com.example.restaurantAssistant.exception.SellException;
import com.example.restaurantAssistant.pojo.Shop;
import com.example.restaurantAssistant.pojo.ShopCategory;
import com.example.restaurantAssistant.repository.ShopRepository;
import com.example.restaurantAssistant.service.ShopCategoryService;
import com.example.restaurantAssistant.service.ShopService;
import com.example.restaurantAssistant.vo.ShopVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository repository;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Override
    public Shop save(Shop shop) {
        return repository.save(shop);
    }

    @Override
    public Shop findOne(Integer shopId) {
        return repository.getReferenceById(shopId);
    }


    @Override
    public List<Shop> findShopsByCategory(Integer cateId) {
        if (cateId == null){
            throw new SellException(ResultEnum.SHOP_CATEGORY_NOT_EXIST);
        }
        //根据分类id查询该分类下所有店铺
        Shop shop = new Shop();
        shop.setCateId(cateId);
        Example<Shop> example = Example.of(shop);

        return repository.findAll(example);
    }

    @Override
    public Page<Shop> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Shop> findAllByRecommend(Pageable pageable, Integer recommend) {
        return repository.findAllByRecommendAndStateOrderByIdAsc(pageable,recommend, StateEnum.STATE_YES.getCode());
    }


    @Override
    public ShopVO findShopVO(Integer id) {
        Optional<Shop> optional = repository.findById(id);
        ShopVO shopVO = new ShopVO();

        //如果不为空 将值赋给shopVO 否则抛出异常
        if (optional.isPresent()){
            BeanUtils.copyProperties(optional.get(),shopVO);
        }else {
            throw new SellException(ResultEnum.SHOP_NOT_EXIST);
        }

        //查询店铺所属分类
        ShopCategory shopCate = shopCategoryService.findOne(shopVO.getId());
        shopVO.setCateName(shopCate.getName());

        return shopVO;
    }


}
