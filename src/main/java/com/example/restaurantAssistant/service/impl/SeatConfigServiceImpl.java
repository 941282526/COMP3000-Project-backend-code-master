package com.example.restaurantAssistant.service.impl;

import com.example.restaurantAssistant.enums.ResultEnum;
import com.example.restaurantAssistant.exception.SellException;
import com.example.restaurantAssistant.pojo.SeatConfig;
import com.example.restaurantAssistant.repository.SeatConfigRepository;
import com.example.restaurantAssistant.service.SeatConfigService;
import com.example.restaurantAssistant.vo.SellerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author yg
 */
@Service
public class SeatConfigServiceImpl implements SeatConfigService {
    
    @Resource
    SeatConfigRepository repository;

    @Override
    public SeatConfig save(SeatConfig seatConfig) {
        return repository.save(seatConfig);
    }

    //删除商品
    @Override
    public void delete(Integer seatConfigId, SellerVO sellerVO) {
        Optional<SeatConfig> optional = repository.findById(seatConfigId);
        if (!optional.isPresent()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        try {
            repository.deleteById(seatConfigId);
        }catch (Exception e){
            throw new SellException(ResultEnum.PRODUCT_EDIT_FAIL);
        }
    }


    @Override
    public SeatConfig findOne(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Page<SeatConfig> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
