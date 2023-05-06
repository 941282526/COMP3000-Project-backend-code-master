package com.example.jingdong.service;

import com.example.jingdong.dto.DinnerDTO;
import com.example.jingdong.dto.SeatDTO;
import com.example.jingdong.form.DinnerForm;

import java.util.Date;
import java.util.List;

/**
 * @author yg
 */
public interface PreDinnerService {

    /**
     * 餐桌库存查询
     *
     * @param dinnerTime 时间段
     * @return 餐桌库存信息
     */
    List<SeatDTO> dinnerSeat(Date dinnerTime);

    /**
     * 用餐状态变更
     *
     * @param userId 用户id
     * @param status   变更状态
     * @return 取消
     */
    Boolean dinnerUpdateStatus(Integer userId, Integer status);

    /**
     * 根据用户id，查询订单信息
     *
     * @param userId 用户id
     * @param status 状态
     * @return 订餐信息
     */
    List<DinnerDTO> dinnerQuery(Integer userId, List<Integer> status);

    /**
     * 查询排位号（当天未使用的订餐数）
     *
     * @param userId 用户id
     * @return 号数
     */
    Integer dinnerQueueNumber(Integer userId);

    /**
     * 排队订餐保存
     *
     * @param dinnerForm 订餐数据
     * @return 排队
     */
    Integer dinnerSave(DinnerForm dinnerForm);
}
