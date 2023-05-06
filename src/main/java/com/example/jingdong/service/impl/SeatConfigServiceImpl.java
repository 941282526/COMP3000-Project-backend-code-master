package com.example.jingdong.service.impl;

import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.pojo.Product;
import com.example.jingdong.pojo.SeatConfig;
import com.example.jingdong.repository.SeatConfigRepository;
import com.example.jingdong.service.SeatConfigService;
import com.example.jingdong.vo.SellerVO;
import org.springframework.beans.BeanUtils;
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
