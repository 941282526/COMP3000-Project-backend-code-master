package com.example.restaurantAssistant.repository;

import com.example.restaurantAssistant.pojo.PreDinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yg
 */
@Repository
public interface PreDinnerRepository extends JpaRepository<PreDinner, Integer> {

    /**
     * 查询订餐信息
     *
     * @param status     用餐状态
     * @return 订餐信息
     */
    List<PreDinner> findByStatusIn(List<Integer> status);

    /**
     * 查询订餐信息
     *
     * @param userId 用户id
     * @param status 用餐状态
     * @return 订餐信息
     */
    List<PreDinner> findByUserIdAndStatusIn(Integer userId, List<Integer> status);

    /**
     * 根据状态查询用餐表信息
     *
     * @param seatId 餐位id
     * @param status 状态
     * @return 用餐信息
     */
    List<PreDinner> findBySeatIdAndStatusInOrderById(Integer seatId, ArrayList<Integer> status);
}
