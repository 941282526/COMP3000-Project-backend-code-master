package com.example.jingdong.service;

import com.example.jingdong.pojo.Product;
import com.example.jingdong.pojo.SeatConfig;
import com.example.jingdong.vo.SellerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author yg
 */
public interface SeatConfigService {

    /**
     * 保存商品信息
     * @param seatConfig   商品对象
     * @return          成功则返回更新的商品信息
     */
    SeatConfig save(SeatConfig seatConfig);

    /**
     * 删除商品 需判断商品和当前操作的用户是否属于同一用户 防止越权
     * @param seatConfigId    需要删除的商品id
     * @param sellerVO     当前操作的用户
     */
    void delete(Integer seatConfigId, SellerVO sellerVO);

    /**
     * 根据商品id查询一条数据
     * @param id    商品id
     * @return      查询到的映射对象
     */
    SeatConfig findOne(Integer id);

    Page<SeatConfig> list(Pageable pageable);
}
