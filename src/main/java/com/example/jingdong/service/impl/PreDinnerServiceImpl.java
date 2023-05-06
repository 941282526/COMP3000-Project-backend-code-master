package com.example.jingdong.service.impl;

import com.example.jingdong.dto.DinnerDTO;
import com.example.jingdong.dto.SeatDTO;
import com.example.jingdong.enums.DinnerStateEnum;
import com.example.jingdong.enums.ResultEnum;
import com.example.jingdong.exception.SellException;
import com.example.jingdong.form.DinnerForm;
import com.example.jingdong.pojo.PreDinner;
import com.example.jingdong.pojo.SeatConfig;
import com.example.jingdong.repository.PreDinnerRepository;
import com.example.jingdong.repository.SeatConfigRepository;
import com.example.jingdong.service.PreDinnerService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yg
 */
@Service
public class PreDinnerServiceImpl implements PreDinnerService {

    @Autowired
    private PreDinnerRepository preDinnerRepository;
    @Autowired
    private SeatConfigRepository seatConfigRepository;

    @Override
    public List<SeatDTO> dinnerSeat(Date dinnerTime) {
        Map<Integer, SeatConfig> seatConfigMap = seatConfigRepository.findAll()
                .stream()
                .collect(Collectors.toMap(SeatConfig::getId, o -> o));

        // 查询预定时间未使用、使用中的数据
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        List<PreDinner> preDinnerList = preDinnerRepository.findByStatusIn(
                Lists.newArrayList(DinnerStateEnum.NOT_USED.getCode(), DinnerStateEnum.IN_USE.getCode()));
        preDinnerList = preDinnerList
                .stream()
                .filter(data -> dateFormat.format(dinnerTime).equals(dateFormat.format(data.getDinnerTime())))
                .collect(Collectors.toList());
        Map<Integer, List<PreDinner>> preDinnerMap = preDinnerList
                .stream()
                .collect(Collectors.groupingBy(PreDinner::getSeatId));

        List<SeatDTO> seatList = Lists.newArrayList();
        seatConfigMap.forEach((k, v) -> {
            List<PreDinner> dinnerList = preDinnerMap.get(k);
            SeatDTO seat = new SeatDTO();
            // 餐桌id
            seat.setKey(k);
            // 判断库存 未使用+使用中-配置库存>5,说明这个时间段没有餐桌
            boolean exist;
            if(CollectionUtils.isEmpty(dinnerList)){
                exist = true;
            }else {
                exist = dinnerList.size() - v.getQuantity() < 5;
            }
            // 餐桌状态
            seat.setStatus(exist);
            // 餐桌描述
            seat.setDesc(exist ? v.getSeatType() : v.getSeatType() + "(已满)");

            seatList.add(seat);
        });
        return seatList;
    }

    /**
     * 持久化订餐信息
     *
     * @param dinnerForm 保存数据
     */
    private void savePreDinner(DinnerForm dinnerForm) {
        PreDinner preDinner = new PreDinner();
        preDinner.setUserId(dinnerForm.getUserId());
        preDinner.setSeatId(dinnerForm.getSeatId());
        preDinner.setStatus(DinnerStateEnum.NOT_USED.getCode());
        preDinner.setDinnerTime(dinnerForm.getDinnerTime());
        preDinnerRepository.save(preDinner);
    }

    @Override
    public Boolean dinnerUpdateStatus(Integer userId, Integer status) {
        List<PreDinner> myPreDinnerList = preDinnerRepository.findByUserIdAndStatusIn(userId, Lists.newArrayList(DinnerStateEnum.NOT_USED.getCode(), DinnerStateEnum.IN_USE.getCode()));
        // 当前未点餐
        if(CollectionUtils.isEmpty(myPreDinnerList)){
            return true;
        }
        PreDinner preDinner = myPreDinnerList.get(0);
        preDinner.setStatus(status);
        preDinnerRepository.save(preDinner);
        return true;
    }

    @Override
    public List<DinnerDTO> dinnerQuery(Integer userId, List<Integer> status) {
        List<PreDinner> preDinnerList = preDinnerRepository.findByUserIdAndStatusIn(userId, status);

        return preDinnerList.stream().map(data -> {
            DinnerDTO dinner = new DinnerDTO();
            BeanUtils.copyProperties(data, dinner);
            return dinner;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer dinnerQueueNumber(Integer userId) {
        List<PreDinner> myPreDinnerList = preDinnerRepository.findByUserIdAndStatusIn(userId, Lists.newArrayList(DinnerStateEnum.NOT_USED.getCode(), DinnerStateEnum.IN_USE.getCode()));
        // 当前未点餐
        if(CollectionUtils.isEmpty(myPreDinnerList)){
            return null;
        }
        PreDinner nowPreDinner = myPreDinnerList.get(0);
        // 使用中
        if(Objects.equals(nowPreDinner.getStatus(), DinnerStateEnum.IN_USE.getCode())){
            return 9999;
        }
        //未使用
        List<PreDinner> preDinnerList = preDinnerRepository.findBySeatIdAndStatusInOrderById(nowPreDinner.getSeatId(),
                Lists.newArrayList(DinnerStateEnum.NOT_USED.getCode(), DinnerStateEnum.IN_USE.getCode()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        List<PreDinner> dinnerList = preDinnerList
                .stream()
                .filter(data -> dateFormat.format(nowPreDinner.getDinnerTime()).equals(dateFormat.format(data.getDinnerTime())))
                .collect(Collectors.toList());
        // 找出当前用户排位序号
        SeatConfig seatConfig = seatConfigRepository.getReferenceById(nowPreDinner.getSeatId());
        int number = seatConfig.getQuantity() * (-1);
        for (PreDinner reDinner : dinnerList) {
            number++;
            if (reDinner.getUserId().equals(userId)) {
                break;
            }
        }
        return number;
    }

    @Override
    public Integer dinnerSave(DinnerForm dinnerForm) {
        List<PreDinner> myPreDinnerList = preDinnerRepository.findByUserIdAndStatusIn(dinnerForm.getUserId(), Lists.newArrayList(DinnerStateEnum.NOT_USED.getCode(), DinnerStateEnum.IN_USE.getCode()));
        // 当前已点餐
        if(!CollectionUtils.isEmpty(myPreDinnerList)){
            throw new SellException(ResultEnum.DINNER_ALREADY_EXIST);
        }
        this.savePreDinner(dinnerForm);
        return dinnerQueueNumber(dinnerForm.getUserId());
    }
}
